package com.easyhz.tutrd.domain.auth.repository

import com.afs.tutrd.core.model.auth.param.RegisterParam
import com.afs.tutrd.core.model.auth.param.RequestVerificationCodeParam
import com.afs.tutrd.core.model.auth.response.RequestVerificationCodeResponse

interface AuthRepository {
    suspend fun requestVerificationCode(param: RequestVerificationCodeParam): Result<RequestVerificationCodeResponse>
    suspend fun isExistUser(verificationId: String, code: String): Result<Boolean>
    suspend fun register(param: RegisterParam): Result<Unit>
}