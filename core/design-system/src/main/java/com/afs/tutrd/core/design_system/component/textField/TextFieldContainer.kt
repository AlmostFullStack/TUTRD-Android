package com.afs.tutrd.core.design_system.component.textField

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.afs.tutrd.core.design_system.theme.AppColors
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.textField.TextFieldState
import com.afs.tutrd.core.design_system.util.textField.TextFieldType


@Composable
internal fun TextFieldContainer(
    modifier: Modifier = Modifier,
    textFieldType: TextFieldType,
    title: String?,
    placeholder: String,
    state: TextFieldState,
    trailingSection: @Composable (() -> Unit)? = null,
    innerTextField: @Composable () -> Unit
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        title?.let {
            TextFieldContainerTitle(
                modifier = Modifier.widthIn(min = 24.dp),
                title = title,
            )
        }
        TextFieldContainerContent(
            modifier = modifier
                .heightIn(min = textFieldType.minHeight)
                .clip(RoundedCornerShape(12.dp))
                .background(color = AppColors.gray5),
            textFieldType = textFieldType,
            state = state,
            placeholder = placeholder,
            trailingSection = trailingSection,
            innerTextField = innerTextField,
        )
    }
}


@Composable
private fun TextFieldContainerTitle(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        modifier = modifier,
        text = title,
        style = AppTypography.Body.b1,
        color = AppColors.tutrdBlack,
        lineHeight = (14 * 1.2).sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2
    )
}

@Composable
private fun TextFieldContainerContent(
    modifier: Modifier = Modifier,
    textFieldType: TextFieldType,
    state: TextFieldState,
    placeholder: String,
    trailingSection: @Composable (() -> Unit)? = null,
    innerTextField: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = textFieldType.verticalAlignment
    ) {
        Box(modifier = Modifier
            .padding(vertical = textFieldType.verticalPadding)
            .weight(1f)) {
            innerTextField()
            if (state == TextFieldState.Default) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 2.dp),
                    text = placeholder,
                    style = AppTypography.Body.b2,
                    color = AppColors.gray2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        if (state != TextFieldState.Default) {
            trailingSection?.invoke()
        }
    }
}