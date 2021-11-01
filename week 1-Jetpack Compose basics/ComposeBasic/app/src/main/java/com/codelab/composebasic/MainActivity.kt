package com.codelab.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
private fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Greetings("Android", "Compose", "World")
    }
}

@Composable
fun Greeting(name: String) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp, 20.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello")
                Text(text = "$name")
            }
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "$name")
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

@Preview(showBackground = true, widthDp = 240)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        MyApp()
    }
}