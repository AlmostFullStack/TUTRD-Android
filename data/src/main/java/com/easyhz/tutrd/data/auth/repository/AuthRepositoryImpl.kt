package com.easyhz.tutrd.data.auth.repository

import android.util.Log
import com.afs.tutrd.core.common.di.dispatcher.AppDispatchers
import com.afs.tutrd.core.common.di.dispatcher.Dispatcher
import com.afs.tutrd.core.common.error.AppError
import com.afs.tutrd.core.model.auth.param.RegisterParam
import com.afs.tutrd.core.model.auth.param.RequestVerificationCodeParam
import com.afs.tutrd.core.model.auth.param.RequestVerificationCodeParam.Companion.phoneNumberToCountryCode
import com.afs.tutrd.core.model.auth.response.RequestVerificationCodeResponse
import com.easyhz.tutrd.data.auth.datasource.local.AuthLocalDataSource
import com.easyhz.tutrd.data.auth.datasource.remote.AuthRemoteDataSource
import com.easyhz.tutrd.data.auth.mapper.toBody
import com.easyhz.tutrd.domain.auth.repository.AuthRepository
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume

class AuthRepositoryImpl @Inject constructor(
    @Dispatcher(AppDispatchers.IO) private val dispatcher: CoroutineDispatcher,
    private val authLocalDataSource: AuthLocalDataSource,
    private val authRemoteDataSource: AuthRemoteDataSource,
): AuthRepository {
    override suspend fun requestVerificationCode(param: RequestVerificationCodeParam): Result<RequestVerificationCodeResponse> {
        return suspendCancellableCoroutine { continuation ->
            try {
                val phoneNumber = param.phoneNumberToCountryCode().getOrNull()
                if (phoneNumber == null) {
                    continuation.resume(Result.failure(Exception("Invalid phone number")))
                    return@suspendCancellableCoroutine
                }

                val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        handleVerificationCompleted(credential, phoneNumber, continuation)
                    }

                    override fun onVerificationFailed(e: FirebaseException) {
                        continuation.resume(Result.failure(e))
                    }

                    override fun onCodeSent(
                        verificationId: String,
                        token: PhoneAuthProvider.ForceResendingToken
                    ) {
                        continuation.resume(
                            Result.success(
                                RequestVerificationCodeResponse.ReturnCodeSent(verificationId = verificationId, phoneNumber = phoneNumber)
                            )
                        )
                    }
                }

                authRemoteDataSource.verifyPhoneNumber(phoneNumber, param.activity, callbacks)

                // 취소
                continuation.invokeOnCancellation {
                    Log.d("SignRepositoryImpl", "requestVerificationCode: cancelled")
                }
            } catch (e: Exception) {
                continuation.resume(Result.failure(e))
            }
        }
    }

    private fun handleVerificationCompleted(
        credential: PhoneAuthCredential,
        phoneNumber: String,
        continuation: CancellableContinuation<Result<RequestVerificationCodeResponse>>
    ) {
        val scope = CoroutineScope(continuation.context)
        scope.launch {
            try {
                val authResult = authRemoteDataSource.signInWithPhone(credential).getOrNull()
                    ?: throw Exception("Sign in failed")
                val idToken = authResult.user?.getIdToken(true)?.await()

                val isExists = authRemoteDataSource.verify("Bearer $idToken").getOrNull()?.userExists ?: throw AppError.UnexpectedError("userExists is null")
                authResult.user?.uid?.let { uid ->
                    continuation.resume(
                        Result.success(
                            RequestVerificationCodeResponse.ReturnUid(isUserExist = isExists, uid = uid, phoneNumber = phoneNumber)
                        )
                    )
                } ?: throw Exception("User is null")
            } catch (e: Exception) {
                continuation.resume(Result.failure(e))
            }
        }
    }

    override suspend fun isExistUser(
        verificationId: String,
        code: String
    ): Result<Boolean> = withContext(dispatcher) {
        return@withContext runCatching {
            val credential =
                authRemoteDataSource.getCredentials(verificationId = verificationId, code = code)
                    .getOrThrow()
            val authResult = authRemoteDataSource.signInWithPhone(credential).getOrThrow()
            val idToken =
                authResult.user?.getIdToken(true)?.await()?.token ?: throw AppError.UnexpectedError(
                    "idToken is null"
                )
            authRemoteDataSource.verify("Bearer $idToken").getOrNull()?.userExists
                ?: throw AppError.UnexpectedError("userExists is null")
        }
    }

    override suspend fun register(param: RegisterParam): Result<Unit> = withContext(dispatcher) {
        runCatching {
            val uid = authRemoteDataSource.getFirebaseUid().getOrThrow()
            val response = authRemoteDataSource.register(body = param.toBody(uid)).getOrThrow()
            authLocalDataSource.updateTokens(response.accessToken, response.refreshToken)
            Unit
        }
    }
}