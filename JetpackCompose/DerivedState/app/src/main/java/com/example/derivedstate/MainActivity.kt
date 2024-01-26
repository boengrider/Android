package com.example.derivedstate

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.derivedstate.ui.theme.DerivedStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DerivedStateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Log.i("TRACKCOMPOSE","Greeting() called")
    val name = remember { mutableStateOf("Jon Doe") }
    val fullName = remember { derivedStateOf { "Mr. ${name.value}" }}





    Column(horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center, modifier = Modifier
            .background(Color.Cyan)) {
        Text(
            text = "Hello ${fullName.value}!",
            modifier = modifier
                .background(Color.Black),
            color = Color.White,
        )

        ChangeName(name = name)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeName(name: MutableState<String>) {
    Log.i("TRACKCOMPOSE", "ChangeName() called")
    var textFieldInput = remember { mutableStateOf("Initial value") }
    TextField(value = textFieldInput.value, onValueChange = { textFieldInput.value = it } )

    Button(onClick = { name.value = textFieldInput.value }) {
        Text("Change name")
    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    DerivedStateTheme {
        Greeting("Android")
    }
}