package com.afs.tutrd.core.design_system.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.button.DefaultButtonColors

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    colors: DefaultButtonColors = DefaultButtonColors.Default,
    contentPadding: PaddingValues = PaddingValues(vertical = 12.dp),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.heightIn(min = 48.dp),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.backgroundColor,
            contentColor = colors.contentColor,
            disabledContainerColor = colors.disabledBackgroundColor,
            disabledContentColor = colors.disabledContentColor
        ),
        contentPadding = contentPadding,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = AppTypography.Main.h3Bold,
            color = colors.contentColor
        )
    }
}

@Preview
@Composable
private fun MainButtonPreview() {
    MainButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Button",
        onClick = { }
    )
}