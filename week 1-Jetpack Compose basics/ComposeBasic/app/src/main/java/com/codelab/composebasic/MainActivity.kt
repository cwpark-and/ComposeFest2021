package com.codelab.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        var shouldShowOnBoarding by remember { mutableStateOf(true)}
        if(shouldShowOnBoarding) {
            OnBoardingScreen() {
                shouldShowOnBoarding = false
            }
        } else {
            Greetings("Android", "Compose", "World")
        }
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = remember { mutableStateOf(false)}
    val extraPadding = if(expanded.value) 40.dp else 0.dp
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp)
        ) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello")
                Text(text = "$name")
            }
            OutlinedButton(onClick = {
                expanded.value = !expanded.value
            }) {
                Text(text = if(expanded.value) "show less" else "show more")
            }
        }
    }

}

@Composable
fun Greetings(vararg names: String) {
    Column(
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        for(name in names) {
            Greeting(name)
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        MyApp()
    }
}