package com.example.androiduidevelopmentwithjetpackcompose2nd

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

//Pass state to a composable function via parameter

@Composable
@Preview
fun App() {

    var radioEnabled = remember { mutableStateOf(true) }

    CardWithRadio(enabled = radioEnabled)


}

@Composable
fun CardWithRadio(enabled: MutableState<Boolean>) {

    Row(modifier = Modifier.background(Color.Magenta)) {
        Text("Hello Compose")
        RadioButton(selected = enabled.value, onClick = { enabled.value = !enabled.value })
    }
}
