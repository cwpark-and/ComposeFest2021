package com.codelab.composelayout.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

val listSize = 100

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