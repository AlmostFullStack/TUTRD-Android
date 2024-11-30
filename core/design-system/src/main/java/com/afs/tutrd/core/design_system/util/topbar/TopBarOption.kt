package com.afs.tutrd.core.design_system.util.topbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.component.icon.AppIcon
import com.afs.tutrd.core.design_system.component.topbar.TopBarTextButton
import com.afs.tutrd.core.design_system.theme.AppColors
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.icon.IconDefault


sealed class TopBarType {
    data class CenterTitle(
        val text: String,
        val style: TextStyle = AppTypography.Main.h2Bold
    ): TopBarType()

    data class LeadingTitle(
        val text: String,
        val style: TextStyle = AppTypography.Main.h2Bold
    ) : TopBarType()

    data class TextButton(
        val text: String,
        val style: TextStyle,
        val onClick: () -> Unit
    ) : TopBarType()

    data class IconButton(
        @DrawableRes val iconId: Int,
        val iconAlignment: Alignment,
        val color: Color? = null,
        val onClick: () -> Unit
    ) : TopBarType()
}

@Composable
fun TopBarType?.Content(
    modifier: Modifier = Modifier
) {
    when(this) {
        is TopBarType.TextButton -> {
            TopBarTextButton(
                modifier = modifier,
                text = this.text,
                style = this.style,
                onClick = this.onClick
            )
        }
        is TopBarType.IconButton -> {
            AppIcon(
                modifier = modifier,
                icon = IconDefault(
                    painter = painterResource(id = this.iconId),
                    size = 32.dp,
                    alignment = this.iconAlignment,
                    color = this.color ?: AppColors.tutrdBlack,
                ),
                onClick = this.onClick
            )
        }
        is TopBarType.CenterTitle -> {
            Text(
                modifier = modifier,
                text = this.text,
                style = this.style
            )
        }
        is TopBarType.LeadingTitle -> {
            Text(
                modifier = modifier.fillMaxWidth().padding(start = 40.dp),
                text = this.text,
                style = this.style
            )
        }
        null -> { }
    }
}