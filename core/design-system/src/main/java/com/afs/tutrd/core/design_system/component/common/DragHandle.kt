package com.afs.tutrd.core.design_system.component.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.R
import com.afs.tutrd.core.design_system.theme.AppColors

@Composable
fun DragHandle(
    modifier: Modifier = Modifier,
    color: Color = AppColors.gray2
) {
    Box(
        modifier = modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth()
            .height(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_drag_handle),
            contentDescription = null,
            tint = color
        )
    }
}

@Preview
@Composable
private fun DragHandlePreview() {
    DragHandle()
}