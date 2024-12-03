package com.afs.tutrd.core.design_system.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.R
import com.afs.tutrd.core.design_system.component.common.Dot
import com.afs.tutrd.core.design_system.theme.AppColors
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.modifier.screenHorizonPadding
import com.afs.tutrd.core.model.role.Role

@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
    title: String,
    role: Role?,
) {
    Row(
        modifier = modifier
            .height(52.dp)
            .fillMaxWidth()
            .screenHorizonPadding(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = AppTypography.Main.h2Bold,
                color = AppColors.tutrdBlack
            )
            role?.let {
                Text(
                    text = it.name,
                    style = AppTypography.Body.b3Bold,
                    color = AppColors.primary
                )
            }
        }

        Row(
            modifier = Modifier
                .heightIn(28.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(AppColors.tutrdBlack)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Dot(
                color = AppColors.tutrdWhite,
                size = 12.dp
            )
            Text(
                text = "모든 수업",
                style = AppTypography.Caption.c1,
                color = AppColors.tutrdWhite
            )
            Icon(
                painter = painterResource(R.drawable.ic_arrow_down),
                contentDescription = null,
                tint = AppColors.tutrdWhite
            )
        }
    }
}

@Preview
@Composable
private fun MainTopBarPreview() {
    MainTopBar(
        title = "타이틀",
        role = Role.TUTOR
    )

}