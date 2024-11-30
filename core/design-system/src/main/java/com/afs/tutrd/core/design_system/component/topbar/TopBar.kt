package com.afs.tutrd.core.design_system.component.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.theme.AppColors
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.topbar.Content
import com.afs.tutrd.core.design_system.util.topbar.TopBarType

@Composable
fun TopBar(
    left: TopBarType? = null,
    title: TopBarType? = null,
    right: TopBarType? = null,
) {
    Box(
        modifier = Modifier
            .heightIn(min = 52.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    ) {
        left.Content(
            modifier = Modifier.align(Alignment.CenterStart)
        )
        title.Content(
            modifier = Modifier.align(Alignment.Center)
        )
        right.Content(
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar(
        left = TopBarType.TextButton(
            "뒤로",
            AppTypography.Body.b1.copy(color = AppColors.primary),
            {}),
        title = TopBarType.CenterTitle("타이틀"),
        right = TopBarType.TextButton(
            "완료",
            AppTypography.Body.b1.copy(color = AppColors.primary),
            {})
    )
}