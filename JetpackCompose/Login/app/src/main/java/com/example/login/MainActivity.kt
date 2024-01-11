package com.example.login
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
              Background()
        }

    }
    
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Background() {
        var newPrefix: String = "Mr."
        var prefix = remember(key1 = newPrefix) {
            newPrefix

        }

        var name by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "$prefix $name")
                TextField(value = name, onValueChange = { name = it })
                Button(onClick = { newPrefix = "Mrs."}) {
                    Text("Change prefix to Mrs.")
                }

            }

    }






    override fun onStart() {
        super.onStart()
        Log.i("TRACKCOMPOSE", "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("TRACKCOMPOSE", "onResume() called")

    }
}

