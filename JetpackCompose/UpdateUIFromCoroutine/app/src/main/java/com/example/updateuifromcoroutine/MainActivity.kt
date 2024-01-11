package com.example.updateuifromcoroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.updateuifromcoroutine.ui.theme.UpdateUIFromCoroutineTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpdateUIFromCoroutineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var output = remember { mutableStateOf(0L) }
                    var textLabel = remember { mutableStateOf("Execute async") }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(horizontalArrangement = Arrangement.Center) {
                            Button(onClick = {
                                textLabel.value = "Executing async..."
                                val responseA = CoroutineScope(Dispatchers.Default).async {
                                    var cycles = 0
                                    var accumulator = 0L
                                    for(i in 1..100) {
                                        accumulator = 0L
                                        for (j in 1..100000000) {
                                            accumulator++
                                        }
                                    }
                                    output.value += accumulator
                                    textLabel.value = "Execute async"
                                }

                            }) {
                                Text(textLabel.value)
                            }

                            Button(onClick = { output.value = 0L }) {
                                Text("Rest")
                            }
                        }

                        Row(horizontalArrangement = Arrangement.Center) {
                            Text(output.value.toString())
                        }
                    }
                }
            }
        }
    }
}

