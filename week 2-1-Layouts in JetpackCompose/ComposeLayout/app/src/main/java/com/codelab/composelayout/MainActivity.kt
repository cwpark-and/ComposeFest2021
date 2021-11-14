package com.codelab.composelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.composelayout.ui.composable.*
import com.codelab.composelayout.ui.modifier.firstBaselineToTop
import com.codelab.composelayout.ui.theme.ComposeLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ComposeLayoutTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    ImageList()
//                }
//            }
            MainLayout {
                BodyContents(Modifier.horizontalScroll(rememberScrollState()))
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            .clickable { }
            .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            //TODO : image goes here
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}


@Composable
fun MainLayout(
    bodyContent: (@Composable () -> Unit)
) {
    var isFavorite = remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "TopBar")
                },
                navigationIcon = {
                     IconButton(onClick = { /*TODO*/ }) {
                         Icon(Icons.Filled.Menu, contentDescription = "menu")
                     }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Search, contentDescription = "search")
                    }
                    IconButton(onClick = {
                        isFavorite.value = !isFavorite.value
                    }) {
                        val isFilled = isFavorite.value
                        val icons = if(isFilled) Icons.Filled.Favorite else Icons.TwoTone.Favorite
                        Icon(icons, contentDescription = "favorite")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "more")
                    }
                },
                elevation = 4.dp
            )
        },
        bottomBar = {
            BottomNavigation(
            ) {
                BottomNavigationItem(
                    icon = {Icon(Icons.Default.Call, contentDescription = "call")},
                    label = {Text(text="CALL")},
                    modifier = Modifier.align(Alignment.CenterVertically),
                    selected = false,
                    onClick = {}
                )
            }
        }
    ) {
        bodyContent()
    }
}

@Composable
fun BodyContents(modifier: Modifier = Modifier) {
//    CustomColumn(modifier = modifier) {
//        Text(text = "Hi there!")
//        Text(text = "Thanks for going through the Layouts codelab")
//    }
    StaggeredGrid(modifier = modifier, rows = 5) {
        for(topic in topics) {
            Chip(modifier = Modifier.padding(8.dp), text= topic)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLayoutTheme {
        MainLayout() {
            BodyContents()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultSimpleListPreview() {
    ComposeLayoutTheme {
        SimpleList()
    }
}

@Preview(showBackground = true)
@Composable
fun FirstBaseLinePreView() {
    ComposeLayoutTheme {
        Text("Hi there", Modifier.firstBaselineToTop(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PaddingPreView() {
    ComposeLayoutTheme {
        Text("Hi there", Modifier.padding(top = 20.dp))
    }
}