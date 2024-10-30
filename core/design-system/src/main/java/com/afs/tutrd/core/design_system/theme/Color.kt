package com.afs.tutrd.core.design_system.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColor(
    val tutrdBlack: Color,
    val tutrdWhite: Color,
    val gray1: Color,
    val gray2: Color,
    val gray3: Color,
    val gray4: Color,
    val gray5: Color,
    val subBackground: Color,
    val primary: Color,
    val ghostWhite: Color
)

internal val LightAppColors = AppColor(
    tutrdBlack = Color(0xFF111111),
    tutrdWhite = Color(0xFFFFFFFF),
    gray1 = Color(0xFF767676),
    gray2 = Color(0xFF999999),
    gray3 = Color(0xFFC9C9C9),
    gray4 = Color(0xFFE5E5EC),
    gray5 = Color(0xFFF1F1F5),
    subBackground = Color(0xFFF7F7FB),
    primary = Color(0xFF1E6FD9),
    ghostWhite = Color(0xFFF7F7FB)
)

internal val DarkAppColors = AppColor(
    tutrdBlack = Color(0xFF111111),
    tutrdWhite = Color(0xFFFFFFFF),
    gray1 = Color(0xFF767676),
    gray2 = Color(0xFF999999),
    gray3 = Color(0xFFC9C9C9),
    gray4 = Color(0xFFE5E5EC),
    gray5 = Color(0xFFF1F1F5),
    subBackground = Color(0xFFF7F7FB),
    primary = Color(0xFF1E6FD9),
    ghostWhite = Color(0xFFF7F7FB)
)

internal val LocalAppColors = staticCompositionLocalOf { LightAppColors }

val AppColors: AppColor
    @Composable
    get() = LocalAppColors.current
