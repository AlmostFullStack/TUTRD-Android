package com.easyhz.tutrd.data.auth.datasource.remote

import android.app.Activity
import com.afs.tutrd.network.model.auth.request.RegisterBody
import com.afs.tutrd.network.model.auth.response.RegisterResponse
import com.afs.tutrd.network.model.auth.response.VerifyResponse
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks

interface AuthRemoteDataSource {
    fun verifyPhoneNumber(phoneNumber: String, activity: Activity, callbacks: OnVerificationStateChangedCallbacks): Result<Unit>
    suspend fun getCredentials(verificationId: String, code: String): Result<PhoneAuthCredential>
    suspend fun signInWithPhone(credential: PhoneAuthCredential): Result<AuthResult>
    suspend fun getFirebaseUid(): Result<String>
    suspend fun verify(idToken: String): Result<VerifyResponse>
    suspend fun register(body: RegisterBody): Result<RegisterResponse>
}