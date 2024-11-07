package com.afs.tutrd.core.design_system.component.sign

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.theme.AppColors
import com.afs.tutrd.core.design_system.theme.AppTypography

@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = AppTypography.Main.h2Bold,
            color = AppColors.tutrdBlack
        )
        Text(
            text = subTitle,
            style = AppTypography.Body.b3,
            color = AppColors.gray1
        )
    }
}

@Preview
@Composable
private fun TitleSectionPreview() {
    TitleSection(
        modifier = Modifier.fillMaxWidth(),
        title = "Title",
        subTitle = "Sub Title"
    )
}