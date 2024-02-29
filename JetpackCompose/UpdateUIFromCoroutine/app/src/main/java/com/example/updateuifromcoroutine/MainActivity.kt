package com.example.updateuifromcoroutine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import com.example.updateuifromcoroutine.ui.theme.UpdateUIFromCoroutineTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {

    private var greetings: String = "Yo"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpdateUIFromCoroutineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }

    fun IncrementNumber(input: Int): Int {
      Log.i("COMPOSE","IncrementNumber() called")

      return input + 1
    }

}

@Composable
fun Test(item: Int, action: (Int, () -> Unit) -> Unit) {

    action {
        Text()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDemo(
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text("Hello")},
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
@Preview
fun TextFieldDemoPreview() {
    val state = remember { mutableStateOf("") }

    TextFieldDemo(
        value = state.value,
        onValueChange = { state.value = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainScreen() {
    Log.i("TRACKCOMPOSE","mainScreen() called")
    var someNumber = remember { mutableStateOf(0) }
    var factorialOfNumber = remember { mutableStateOf("5") }
    var factorialResult = remember { mutableStateOf(0) }


    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        Button(onClick = { ++someNumber.value }) {
            Text("Increment number")
        }

        Text(someNumber.value.toString())

        Button(onClick = {
            GlobalScope.launch(context = Dispatchers.Main) {
                println(Thread.currentThread().name)
            }

            GlobalScope.launch {
                println(Thread.currentThread().name)
            }
        }) {
            Text("Get thread context with delay")
        }

        OutlinedTextField(value = factorialOfNumber.value.toString(), onValueChange = { if(it.isDigitsOnly()) factorialOfNumber.value = it }, label = { Text("Label")}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number
        ))

        Row {
            Button(onClick = {
                if(factorialOfNumber.value.toInt() > 0) {
                    var tmp: Int = 1
                    (2..factorialOfNumber.value.toInt()).forEach {
                        tmp *= it
                    }

                    factorialResult.value = tmp
                } else {

                    factorialResult.value = 0
                }
            }) {
                Text("Compute factorial")
            }

            Text(factorialResult.value.toString())
        }


    }

}

@Composable
@Preview(showSystemUi = true)
fun mainScreenPreview() {
    mainScreen()
}



