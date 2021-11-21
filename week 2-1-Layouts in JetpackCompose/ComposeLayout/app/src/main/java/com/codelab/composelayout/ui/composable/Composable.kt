package com.codelab.composelayout.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast
import coil.compose.rememberImagePainter
import com.codelab.composelayout.ui.theme.ComposeLayoutTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val listSize = 100

val topics = listOf(
    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
    "Religion", "Social sciences", "Technology", "TV", "Writing"
)

@Composable
fun SimpleList() {
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState
    ) {
        items(listSize) {

        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter =rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text("Item $index", modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ImageList() {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        ButtonsLayout(coroutineScope = coroutineScope, scrollState = scrollState)
        LazyColumn(
            state = scrollState
        ) {
            items(listSize) {
                ImageListItem(index = it)
            }
        }
    }

}

@Composable
fun ButtonsLayout(coroutineScope: CoroutineScope, scrollState: LazyListState) {
    Row{
        Button(
            onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }
        ) {
            Text(text = "scroll to the Top")
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }
        ) {
            Text(text = "scroll to the Bottom")
        }
    }
}

@Composable
fun CustomColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier.padding(4.dp),
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        var yPosition = 0

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height
            }
        }
    }
}

@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
) {
    Layout (
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val rowWidths = IntArray(rows) {0} // 행의 각 composable 객체의 width
        val rowHeight = IntArray(rows) {0} // 행의 각 composable 객체의 height

        val placeables = measurables.mapIndexed { index, measurable ->
            val placeable = measurable.measure(constraints)
            val row = index % rows
            rowWidths[row] += placeable.width
            rowHeight[row] = Math.max(rowHeight[row], placeable.height)

            placeable
        }

        val width = rowWidths.maxOrNull()?.coerceIn(constraints.minWidth .. constraints.maxWidth) ?: constraints.minWidth
        val height = rowHeight.sumOf{it}.coerceIn(constraints.minHeight .. constraints.maxHeight)

        val rowY = IntArray(rows){0}

        for(i in 1 until rows) {
            rowY[i] = rowY[i-1] + rowHeight[i-1]
        }

        layout(width = width, height = height) {
            val rowX = IntArray(rows) {0}

            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.placeRelative(
                    x = rowX[row],
                    y = rowY[row]
                )
                rowX[row] += placeable.width
            }
        }
    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String
) {
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
         Row(
             modifier = Modifier
                 .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
             verticalAlignment = Alignment.CenterVertically
         ) {
             Box(modifier = Modifier
                 .size(16.dp)
                 .background(color = MaterialTheme.colors.secondary))
             Spacer(modifier = Modifier.width(4.dp))
             Text(text)
         }
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        val (button1, button2, text) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }) {
            Text(text = "Button 1")
        }

        Text(text = "Text", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 10.dp)
            centerHorizontallyTo(parent)
        })

        val barrrier = createEndBarrier(button1, text)
        Button(
            onClick = { /*TODO*/ },
            Modifier.constrainAs(button2) {
                top.linkTo(button1.top)
                start.linkTo(barrrier)
            }) {
            Text(text = "Button 2")
        }
    }
}

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout {
        val text = createRef()

        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(
            text = "This is a very very very very very very very very very very very long text",
            Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)
                width = Dimension.preferredWrapContent
            }
        )
    }
}

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if(maxWidth < maxHeight) {
            decoupledConstraints(margin = 16.dp)
        } else {
            decoupledConstraints(margin = 32.dp)
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /*TODO*/ },
                Modifier.layoutId("button")
            ) {
                Text(text = "button")
            }

            Text("text", Modifier.layoutId("text"))
        }
    }
}

@Composable
fun TwoText() {
    ConstraintLayout(
        modifier = Modifier.height(IntrinsicSize.Min)
    ) {
        val (text1, text2, divider) = createRefs()

        Text("Hi", Modifier.constrainAs(text1) {
            start.linkTo(parent.start)
            end.linkTo(divider.start)
        }.padding(horizontal = 16.dp))

        Divider(color = Color.Black, modifier = Modifier.fillMaxHeight().width(1.dp).constrainAs(divider) {
            centerHorizontallyTo(parent)
        })

        Text("There", Modifier.constrainAs(text2) {
            start.linkTo(divider.end)
            end.linkTo(parent.end)
        }.padding(horizontal = 16.dp))
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }

        constrain(text) {
            top.linkTo(button.bottom, margin = margin)
        }
    }
}

@Preview
@Composable
fun IntrinsicsPreview() {
    ComposeLayoutTheme {
        TwoText()
    }
}

@Preview
@Composable
fun ConstraintPreView() {
    ComposeLayoutTheme {
        DecoupledConstraintLayout()
    }
}

@Preview
@Composable
fun ChipPreview() {
    ComposeLayoutTheme {
        Chip(text = "Hi, there")
    }
}

@Preview
@Composable
fun StaggeredGridPreview() {
    ComposeLayoutTheme {
        StaggeredGrid() {
            for(topic in topics) {
                Chip(modifier = Modifier.padding(8.dp), text= topic)
            }
        }
    }
}