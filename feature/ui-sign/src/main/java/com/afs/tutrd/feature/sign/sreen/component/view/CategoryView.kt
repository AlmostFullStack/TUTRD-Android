package com.afs.tutrd.feature.sign.sreen.component.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.component.chip.Chip
import com.afs.tutrd.core.design_system.component.sign.TitleSection
import com.afs.tutrd.core.design_system.theme.AppColors
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.modifier.screenHorizonPadding
import com.afs.tutrd.core.model.category.Subcategory
import com.afs.tutrd.feature.sign.util.sign.SignStep

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryView(
    modifier: Modifier = Modifier,
    step: SignStep,
    tabs: List<String>,
    contents: List<Subcategory>,
    selectedIndex: Int,
    selectedSubcategory: List<Subcategory>,
    onClickCategory: (Int) -> Unit,
    onClickSubcategory: (Subcategory) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TitleSection(
            modifier = Modifier
                .fillMaxWidth()
                .screenHorizonPadding(),
            title = stringResource(id = step.titleId),
            subTitle = stringResource(id = step.subTitleId)
        )

        TabRow(
            selectedTabIndex = selectedIndex,
            containerColor = Color.Transparent,
            contentColor = AppColors.tutrdBlack,
            divider = {
                HorizontalDivider(
                    thickness = 0.4.dp,
                    color = AppColors.gray2
                )
            },
            indicator = { tabPositions ->
                if (tabPositions.isEmpty()) return@TabRow
                TabRowDefaults.PrimaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex]),
                    width = 52.dp,
                    color = AppColors.tutrdBlack
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = { onClickCategory(index) },
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        text = tab,
                        style = AppTypography.Body.b2Bold
                    )
                }
            }
        }

        FlowRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            maxItemsInEachRow = 6
        ) {
            contents.forEach { subcategory ->
                Chip(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = subcategory.name,
                    isSelected = selectedSubcategory.contains(subcategory),
                ) { onClickSubcategory(subcategory) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryViewPreview() {
    Box(modifier = Modifier.size(375.dp, 812.dp)) {
        CategoryView(
            step = SignStep.CATEGORY,
            selectedSubcategory = listOf(
                Subcategory("1", "영어"),
            ),
            tabs = listOf("외국어", "Tab 2", "Tab 3"),
            contents = listOf(
                Subcategory("1", "영어"),
                Subcategory("12", "영어"),
                Subcategory("13", "영어sdfasdfdsa"),
                Subcategory("41", "영sdfsadf어"),
                Subcategory("13", "영dsf어"),
            ),
            selectedIndex = 0,
            onClickCategory = {},
            onClickSubcategory = { }
        )
    }

}