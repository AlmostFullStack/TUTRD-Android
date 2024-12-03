package com.afs.tutrd.feature.sign.sreen.component.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.component.sign.TitleSection
import com.afs.tutrd.core.design_system.component.textField.MainTextField
import com.afs.tutrd.core.design_system.R
import com.afs.tutrd.core.design_system.theme.AppColors
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.modifier.noRippleClickable
import com.afs.tutrd.feature.sign.util.sign.SignStep

@Composable
internal fun PhoneView(
    modifier: Modifier = Modifier,
    step: SignStep,
    phoneNumber: String,
    onChangePhoneNumber: (String) -> Unit,
    isSentVerificationCode: Boolean,
    onClickSendVerificationCode: () -> Unit,
    verificationCode: String,
    onChangeVerificationCode: (String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        TitleSection(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = step.titleId),
            subTitle = stringResource(id = step.subTitleId)
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MainTextField(
                value = phoneNumber,
                onValueChange = onChangePhoneNumber,
                title = null,
                placeholder = stringResource(id = R.string.sign_phone_hint),
                isFilled = false,
                singleLine = true,
                trailingSection = {
                    Box(
                        modifier = Modifier
                            .heightIn(32.dp)
                            .noRippleClickable { onClickSendVerificationCode() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id =
                                if (isSentVerificationCode) R.string.sign_phone_resend_verification_code
                                else R.string.sign_phone_send_verification_code
                            ),
                            style = AppTypography.Body.b2.copy(
                                color = AppColors.primary,
                            )
                        )
                    }
                }
            )
            AnimatedVisibility(
                visible = isSentVerificationCode,
                label = "verificationCode",
            ) {
                MainTextField(
                    value = verificationCode,
                    onValueChange = onChangeVerificationCode,
                    title = null,
                    placeholder = stringResource(id = R.string.sign_phone_verification_code_hint),
                    isFilled = false,
                    singleLine = true,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PhoneViewPreview() {
    PhoneView(
        modifier = Modifier.fillMaxWidth(),
        step = SignStep.PHONE,
        phoneNumber = "123",
        onChangePhoneNumber = {},
        isSentVerificationCode = true,
        onClickSendVerificationCode = {},
        verificationCode = "123",
        onChangeVerificationCode = {}
    )
}