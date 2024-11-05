package com.afs.tutrd.core.common.error


sealed class AppError: Throwable() {
    /* 예상하지 못한 에러 */
    data class UnexpectedError(override val message: String) : AppError()

    /* network 에러 */
    data object NetworkConnectionError : AppError() {
        @JvmStatic
        private fun readResolve(): Any = NetworkConnectionError
    }
}