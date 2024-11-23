package com.afs.tutrd.core.design_system.component.chip

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.R
import com.afs.tutrd.core.design_system.theme.AppColors
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.chip.ChipType

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    chipType: ChipType = ChipType.DEFAULT,
    isSelected: Boolean = false,
    text: String,
    onClick: () -> Unit
) {
    val backgroundColor = getChipBackgroundColor(isSelected)
    val textColor = getChipTextColor(isSelected)
    Row(
        modifier = modifier
            .heightIn(min = 32.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(
                color = backgroundColor
            )
            .clickable { onClick() }
            .padding(vertical = 6.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = textColor,
            style = AppTypography.Body.b3,
        )
        if (chipType == ChipType.CLOSE) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close_small),
                contentDescription = null,
                tint = textColor
            )
        }
    }
}

@Composable
private fun getChipBackgroundColor(isSelected: Boolean): Color {
    return animateColorAsState(
        targetValue = if (isSelected) AppColors.primary else AppColors.gray5,
        label = "backgroundColor"
    ).value
}

@Composable
private fun getChipTextColor(isSelected: Boolean): Color {
    return animateColorAsState(
        targetValue = if (isSelected) AppColors.tutrdWhite else AppColors.gray1,
        label = "textColor"
    ).value
}

@Preview
@Composable
private fun ChipPreview() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Chip(
            text = "Chip",
        ) { }
        Chip(
            text = "Chip",
            isSelected = true
        ) { }
        Chip(
            text = "Chip",
            isSelected = true,
            chipType = ChipType.CLOSE
        ) { }
    }

}