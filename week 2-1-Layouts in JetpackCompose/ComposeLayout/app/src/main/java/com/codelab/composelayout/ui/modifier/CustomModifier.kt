package com.codelab.composelayout.ui.modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints = constraints)

        check(placeable[FirstBaseline] != AlignmentLine.Unspecified) // Composable 객체가 firstbaseline 속성(?)을 가지고 있는지 체크
        val firstBaseline = placeable[FirstBaseline]

        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline // 해당 composable 객체의 top padding
        val height = placeable.height + placeableY
        layout(placeable.width, height = height) {
            placeable.placeRelative(0, placeableY)
        }
    }
)