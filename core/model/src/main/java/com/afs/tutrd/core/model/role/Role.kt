package com.afs.tutrd.core.model.role

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.afs.tutrd.core.design_system.R

enum class Role(
    @DrawableRes val iconId: Int,
    @StringRes val titleId: Int,
) {
    TUTEE(
        iconId = R.drawable.ic_tutee,
        titleId = R.string.sign_role_tutee,
    ),
    TUTOR(
        iconId = R.drawable.ic_tutor,
        titleId = R.string.sign_role_tutor,
    );
}