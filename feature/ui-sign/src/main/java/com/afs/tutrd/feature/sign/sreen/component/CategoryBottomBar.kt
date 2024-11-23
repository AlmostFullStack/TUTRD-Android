package com.afs.tutrd.feature.sign.sreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.R
import com.afs.tutrd.core.design_system.component.button.MainButton
import com.afs.tutrd.core.design_system.component.chip.Chip
import com.afs.tutrd.core.design_system.theme.AppColors
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.button.DefaultButtonColors
import com.afs.tutrd.core.design_system.util.chip.ChipType
import com.afs.tutrd.core.design_system.util.modifier.dropShadow
import com.afs.tutrd.core.design_system.util.modifier.screenHorizonPadding
import com.afs.tutrd.core.model.category.Subcategory
import com.afs.tutrd.feature.sign.util.sign.SignStep

@Composable
fun CategoryBottomBar(
    modifier: Modifier = Modifier,
    selectedSubcategory: List<Subcategory>,
    onClickChip: (Subcategory) -> Unit,
    onClickSkip: () -> Unit,
    onClickNext: () -> Unit
) {
    Column(
        modifier = modifier
            .dropShadow(
                shape = RectangleShape,
                offsetY = (-4).dp
            )
            .fillMaxWidth()
            .background(AppColors.tutrdWhite)
            .screenHorizonPadding()
            .padding(vertical = 16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.category_selected, selectedSubcategory.size),
                style = AppTypography.Body.b3Bold
            )
            LazyRow(
                modifier = Modifier.heightIn(min = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(selectedSubcategory) {
                    Chip(
                        modifier = Modifier.animateItem(),
                        text = it.name,
                        isSelected = true,
                        chipType = ChipType.CLOSE
                    ) { onClickChip(it) }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SignStep.CATEGORY.skipButtonStringId?.let {
                MainButton(
                    modifier = Modifier.widthIn(min = 100.dp),
                    text = stringResource(id = it),
                    colors = DefaultButtonColors.Light,
                    onClick = onClickSkip
                )
            }
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = stringResource(id = SignStep.CATEGORY.nextButtonStringId),
                onClick = onClickNext
            )
        }
    }
}

@Preview
@Composable
private fun CategoryBottomBarPreview() {
    CategoryBottomBar(
        selectedSubcategory = listOf(Subcategory("12", "영어")),
        onClickChip = { },
        onClickSkip = { }
    ) { }
}