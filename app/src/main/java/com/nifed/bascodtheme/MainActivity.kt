package com.nifed.bascodtheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nifed.bascodtheme.ui.theme.BasCodThemeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasCodThemeTheme {
                // A surface container using the 'background' color from the theme
                    MyApp()
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("Android", "Compose", "Me")
    ) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        for (name in names){
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val expanded = remember{ mutableStateOf(false)}
    val extraPadding = if (expanded.value) 45.dp else 0.dp

    Surface(color = MaterialTheme.colorScheme.primary,
    modifier = modifier.padding(5.dp)) {
        Row(modifier = Modifier.padding(14.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello, ")
                Text(text = name)
            }

            ElevatedButton(onClick = { expanded.value = !expanded.value }){
                Text(text = if(expanded.value) "Show less" else "Show more")
            }
        }

    }
}

@Preview
@Composable
fun ItemPreview(){
    BasCodThemeTheme {
        Greeting(name = "PlaceHolder")
    }
}

@Preview(showBackground = true, name = "My text preview", widthDp = 320)
@Composable
fun MyAppPreview() {
    BasCodThemeTheme {
        MyApp()
    }
}