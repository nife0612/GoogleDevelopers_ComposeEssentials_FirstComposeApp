package com.nifed.bascodtheme

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
fun MyApp(modifier: Modifier = Modifier) {
    val showOnboardScreen = rememberSaveable {
        mutableStateOf(true)
    }

    Surface(modifier = modifier) {

        if (showOnboardScreen.value)
            OnboardingScreen(onContinueClicked = {showOnboardScreen.value = false})
        else
            Greetings()

    }
}

@Composable
fun OnboardingScreen(onContinueClicked : () -> Unit, modifier: Modifier = Modifier){
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Welcome to the Basics Codelab!")
        ElevatedButton(onClick = onContinueClicked, modifier.padding(20.dp)) {
            Text(text = "Continue")
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names : List<String> = List(1000){"$it"}
) {
    LazyColumn(
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxSize()
    ) {
        items(items = names){name ->
            Greeting(name = name)

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(5.dp)
    ){
        CardContent(name)
    }
}

@Composable
fun CardContent(name: String){
    val expanded = rememberSaveable { mutableStateOf(false)}

    Row(
        modifier = Modifier
            .padding(14.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)

        ) {
            Text(text = "Hello, ")
            Text(text = name, style= MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold
            ))
            if(expanded.value){
                Text(
                    text = ("Text that repeats. ").repeat(12)
                )
            }
        }

        IconButton(onClick = { expanded.value = !expanded.value }){
            val expandedIcon = if(expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore
            val expandedDescription = stringResource(id = if(expanded.value) R.string.expand_less else R.string.expand_more)
            Icon(
                expandedIcon,
                contentDescription = expandedDescription
            )
        }
    }
}


@Preview(showBackground = true, name = "My text preview")
@Composable
fun MyAppPreview() {
    BasCodThemeTheme {
        MyApp()
    }
}

@Preview(showBackground = true, heightDp = 120)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, heightDp = 120)
@Composable
fun OnBoardingPreview(){
    BasCodThemeTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(heightDp = 320, showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, heightDp = 320, showBackground = true)
@Composable
fun ItemsPreview(){
    BasCodThemeTheme {
        Greetings()
    }
}

