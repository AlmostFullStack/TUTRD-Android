package com.afs.tutrd.core.common.error

import androidx.annotation.StringRes
import com.afs.tutrd.core.common.R
@StringRes
fun Throwable.handleError(): Int {
    return when(this) {
        is AppError.UnexpectedError -> R.string.error_unexpected
        is AppError.NetworkConnectionError -> R.string.error_network_connection
        is HttpError.BadRequestError -> R.string.error_bad_request
        is HttpError.NotFoundError -> R.string.error_not_found
        is HttpError.InternalServerError -> R.string.error_internal_server
        else -> R.string.error_unexpected
    }
}