package com.easyhz.tutrd.data.auth.datasource.remote

import android.app.Activity
import com.afs.tutrd.network.api.auth.AuthApi
import com.afs.tutrd.network.model.auth.request.RegisterBody
import com.afs.tutrd.network.model.auth.response.RegisterResponse
import com.afs.tutrd.network.model.auth.response.VerifyResponse
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val authApi: AuthApi
): AuthRemoteDataSource {

    override fun verifyPhoneNumber(
        phoneNumber: String,
        activity: Activity,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ): Result<Unit> = runCatching {
        val optionsCompat = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(optionsCompat)
        firebaseAuth.setLanguageCode("kr")
    }

    override suspend fun getCredentials(verificationId: String, code: String): Result<PhoneAuthCredential> = runCatching {
        PhoneAuthProvider.getCredential(verificationId, code)
    }

    override suspend fun signInWithPhone(credential: PhoneAuthCredential): Result<AuthResult> = runCatching {
        return@runCatching firebaseAuth.signInWithCredential(credential).await()
    }

    override suspend fun getFirebaseUid(): Result<String> = runCatching {
        return@runCatching firebaseAuth.currentUser?.uid ?: throw Exception("User is not signed in")
    }

    override suspend fun verify(idToken: String): Result<VerifyResponse> {
        return authApi.verify(token = idToken)
    }

    override suspend fun register(body: RegisterBody): Result<RegisterResponse> {
        return authApi.register(body = body)
    }
}