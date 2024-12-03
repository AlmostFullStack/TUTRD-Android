package com.afs.tutrd.core.design_system.component.textField

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.theme.AppColors
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.textField.TextFieldType
import com.afs.tutrd.core.design_system.util.textField.getTextFieldState


@Composable
fun MainTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    title: String?,
    placeholder: String,
    isFilled: Boolean,
    readOnly: Boolean = false,
    singleLine: Boolean,
    textFieldType: TextFieldType = TextFieldType.SINGLE,
    minLines: Int = 1,
    trailingSection: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val state = getTextFieldState(text = value, isFilled = isFilled)

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = AppTypography.Body.b2.copy(
            color = AppColors.tutrdBlack
        ),
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        minLines = minLines,
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            TextFieldContainer(
                modifier = Modifier
                    .heightIn(min = 48.dp),
                title = title,
                state = state,
                placeholder = placeholder,
                trailingSection = trailingSection,
                innerTextField = innerTextField,
                textFieldType = textFieldType
            )
        }
    )
}

@Preview
@Composable
private fun MainTextFieldPrev() {
    MainTextField(
        value = "내용이 잇음",
        onValueChange = { },
        title = null,
        placeholder = "내용으 입력",
        isFilled = false,
        singleLine = true,
    )
}

@Preview
@Composable
private fun MainTextFieldEmptyPrev() {
    MainTextField(
        value = "",
        onValueChange = { },
        title = null,
        placeholder = "내용으 입력",
        isFilled = false,
        singleLine = true,
    )
}

@Preview
@Composable
private fun MainTextFieldTrailingPrev() {
    MainTextField(
        value = "123",
        onValueChange = { },
        title = null,
        placeholder = "내용으 입력",
        isFilled = false,
        singleLine = true,
        trailingSection = {
            Box(
                modifier = Modifier
                    .heightIn(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "인증번호 전송",
                    style = AppTypography.Body.b2.copy(
                        color = AppColors.primary,
                    )
                )
            }
        }
    )
}
