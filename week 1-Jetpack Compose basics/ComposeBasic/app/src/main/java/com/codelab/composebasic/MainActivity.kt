package com.codelab.composebasic

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun OnBoardingScreen(onNextClicked: () -> Unit) {
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("This is OnBoarding Screen")
            Button(
                modifier = Modifier.padding(15.dp),
                onClick = onNextClicked) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
private fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true)}
        if(shouldShowOnBoarding) {
            OnBoardingScreen() {
                shouldShowOnBoarding = false
            }
        } else {
            Greetings()
        }
    }
}

@Composable
private fun CardGreeting(name: String) {
    Card(
        modifier = Modifier.padding(4.dp, 2.dp)
    ) {
        Greeting(name = name)
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = rememberSaveable { mutableStateOf(false)}
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(modifier = Modifier
                .weight(1f)
                ) {
                Text(text = "Hello")
                Text(text = "$name", style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
                if(expanded.value) {
                    Text(text = stringResource(id = R.string.more_text).repeat(3))
                }
            }
            IconButton(
                onClick = { expanded.value = !expanded.value },
            ) {
                Icon(
                    imageVector = if(expanded.value) {
                        Icons.Filled.ExpandLess
                    } else {
                        Icons.Filled.ExpandMore
                   },
                    contentDescription = if(expanded.value) {
                        stringResource(id = R.string.show_less)
                    } else {
                        stringResource(id = R.string.show_more)
                    }
                )
            }
        }
    }

}

@Composable
fun Greetings(names: List<String> = List(1000) {"$it"}) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        items(items = names) { name ->
            CardGreeting(name)
        }
    }

//    Column(
//        modifier = Modifier.padding(vertical = 2.dp)
//    ) {
//        for(name in names) {
//            Greeting(name)
//        }
//    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun OnBoardingPreview() {
    ComposeBasicTheme() {
        MyApp()
    }
}