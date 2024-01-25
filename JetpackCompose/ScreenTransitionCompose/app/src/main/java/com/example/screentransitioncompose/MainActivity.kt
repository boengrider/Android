package com.example.screentransitioncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.screentransitioncompose.ui.theme.ScreenTransitionComposeTheme
import java.math.BigInteger
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity


enum class Screens {
    ScreenA, ScreenB
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Bundle size -> ${savedInstanceState?.size()}")
        setContent {
            ScreenTransitionComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    ScreenMultiplexor(this.componentName.shortClassName)
                }
            }
        }
    }
}

class SecondaryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("Secondary activity started")
        setContent {
            ScreenTransitionComposeTheme {
                Surface(
                    color = Color.Black
                ) {

                }
            }
        }
    }
}

/*************************************************
 * Approach B: Only multiplexor function
 * Maintain state in this function
 *************************************************/

@Composable
fun ScreenMultiplexor(currentActivity: String) {

    var currentActivity = rememberSaveable { mutableStateOf(currentActivity) }
    var currentScreen = rememberSaveable { mutableStateOf(Screens.ScreenA.ordinal) } // Start on ScreenA

    /**
    //Slow down the composition
    Column(modifier = Modifier.width(40.dp).height(40.dp).background(Color.Red)) {
    (1..1000).forEach {
    (1..5).forEach {
    println(it*it)
    }
    }
    }
     **/

    Column() {


        if (currentScreen.value == Screens.ScreenA.ordinal) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Blue),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = currentActivity.value, modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(20.dp), textAlign = TextAlign.Center
                )
                Button(onClick = { currentScreen.value = Screens.ScreenB.ordinal }) {
                    Text("Transition to screen B")
                }

                Button(onClick = { startActivity(MainActivity, ) }) {
                    Text("Secondary activity")
                }
            }

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Cyan),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = currentActivity.value, modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(20.dp), textAlign = TextAlign.Center
                )
                Button(onClick = { currentScreen.value = Screens.ScreenA.ordinal }) {
                    Text("Transition to screen A")
                }

                Button(onClick = { /*TODO*/ }) {
                    Text("Secondary activity")
                }
            }

        }
    }
}

/*************************************************
 *Approach A: Separate functions. Pass parameters
 *************************************************/
/**
@Composable
fun ScreenMultiplexor(currentActivity: String) {

    var currentActivity = remember { mutableStateOf(currentActivity) }
    var showScreen = remember { mutableStateOf(Screens.ScreenA.ordinal) } // Start on ScreenA

    /**
    //Slow down the composition
    Column(modifier = Modifier.width(40.dp).height(40.dp).background(Color.Red)) {
        (1..1000).forEach {
            (1..5).forEach {
                println(it*it)
            }
        }
    }
    **/

    if (showScreen.value == Screens.ScreenA.ordinal) {

        ScreenA(showScreen)

    } else {

        ScreenB(showScreen)

    }



}

@Composable
fun ScreenB(screenSelector: MutableState<Int>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = { screenSelector.value = Screens.ScreenA.ordinal }) {
            Text("Transition to screen A")
        }
    }
}

@Composable
fun ScreenA(screenSelector: MutableState<Int>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = { screenSelector.value = Screens.ScreenB.ordinal }) {
            Text("Transition to screen B")
        }
    }

}
 **/

