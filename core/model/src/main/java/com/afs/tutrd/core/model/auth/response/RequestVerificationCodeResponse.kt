package com.afs.tutrd.core.model.auth.response

sealed class RequestVerificationCodeResponse {
    data class ReturnCodeSent(val verificationId: String, val phoneNumber: String) : RequestVerificationCodeResponse()
    data class ReturnUid(val isUserExist: Boolean, val uid: String, val phoneNumber: String): RequestVerificationCodeResponse()
}