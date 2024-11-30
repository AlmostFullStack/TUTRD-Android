package com.afs.tutrd.core.design_system.component.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.modifier.noRippleClickable


@Composable
internal fun TopBarTextButton(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = AppTypography.Body.b1,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
        .size(32.dp)
        .noRippleClickable { onClick() }
    ) {
        Text(
            text = text, style = style, modifier = Modifier.align(Alignment.Center)
        )
    }
}
