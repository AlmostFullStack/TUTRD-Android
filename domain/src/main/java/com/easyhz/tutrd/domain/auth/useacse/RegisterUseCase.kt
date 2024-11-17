package com.easyhz.tutrd.domain.auth.useacse

import com.afs.tutrd.core.model.auth.param.RegisterParam
import com.easyhz.tutrd.domain.auth.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(param: RegisterParam): Result<Unit> {
        return authRepository.register(param)
    }
}