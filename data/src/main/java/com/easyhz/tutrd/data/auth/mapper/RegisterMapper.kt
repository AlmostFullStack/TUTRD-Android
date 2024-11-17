package com.easyhz.tutrd.data.auth.mapper

import com.afs.tutrd.core.model.auth.param.RegisterParam
import com.afs.tutrd.network.model.auth.request.RegisterBody

internal fun RegisterParam.toBody(uid: String) = RegisterBody(
    uid = uid,
    name = name,
    phone = phone,
    specialty = specialty
)