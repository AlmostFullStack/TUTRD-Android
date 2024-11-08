package com.afs.tutrd.core.design_system.component.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.util.icon.IconDefault
import com.afs.tutrd.core.design_system.util.modifier.noRippleClickable

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    icon: IconDefault,
    onClick: () -> Unit = {}
) {
    val haptic = LocalHapticFeedback.current
    Box(modifier = modifier
        .size(icon.size.coerceAtLeast(32.dp))
        .noRippleClickable {
            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            onClick()
        }) {
        when {
            icon.imageVector != null -> {
                Icon(
                    imageVector = icon.imageVector,
                    contentDescription = null,
                    modifier = Modifier
                        .align(icon.alignment)
                        .size(icon.size)
                )
            }

            icon.painter != null -> {
                Icon(
                    painter = icon.painter,
                    contentDescription = null,
                    modifier = Modifier
                        .align(icon.alignment)
                        .size(icon.size),
                    tint = icon.color
                )
            }
        }
    }
}