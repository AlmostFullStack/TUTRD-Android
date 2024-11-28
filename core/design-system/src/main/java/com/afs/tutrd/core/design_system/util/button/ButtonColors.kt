package com.afs.tutrd.core.design_system.util.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.afs.tutrd.core.design_system.theme.AppColors
import com.afs.tutrd.core.design_system.theme.DefaultAppColors

@Immutable
data class DefaultButtonColors(
    val backgroundColor: Color,
    val contentColor: Color,
    val disabledBackgroundColor: Color,
    val disabledContentColor: Color
) {
    companion object {
        val Default = DefaultButtonColors(
            backgroundColor = DefaultAppColors.primary,
            contentColor = DefaultAppColors.tutrdWhite,
            disabledBackgroundColor = DefaultAppColors.gray4,
            disabledContentColor = DefaultAppColors.gray1
        )
        val Light = DefaultButtonColors(
            backgroundColor = DefaultAppColors.gray4,
            contentColor = DefaultAppColors.gray1,
            disabledBackgroundColor = DefaultAppColors.gray5,
            disabledContentColor = DefaultAppColors.gray2
        )
    }
}

@Composable
fun defaultButtonColors(
    backgroundColor: Color = AppColors.primary,
    contentColor: Color = AppColors.tutrdWhite,
    disabledBackgroundColor: Color = AppColors.gray4,
    disabledContentColor: Color = AppColors.gray1
): DefaultButtonColors = DefaultButtonColors(
    backgroundColor = backgroundColor,
    contentColor = contentColor,
    disabledBackgroundColor = disabledBackgroundColor,
    disabledContentColor = disabledContentColor
)
