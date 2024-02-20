package com.example.basicscodelab


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme



//State and MutableState are interfaces that hold some value and trigger UI updates (recompositions) whenever that value changes.
//However this state is global or stored within a class. Either way, it's not recommended
var observedState = mutableStateOf(true)
@Composable
fun TrackGlobalStateWithoutRemember() {

    Column {
        Row {
            Text("Value of observedState variable is ")
            Text(
                text = observedState.value.toString(),
                color = if (observedState.value) Color.Green else Color.Red
            )
        }

        Button(onClick = { observedState.value = !observedState.value }) {
            Text("Change global state")
        }
    }

}


//To preserve state across recompositions, remember the mutable state using remember.
@Composable
fun TrackGlobalStateUsingRemember() {
    var observedState = remember { mutableStateOf(true) }
    Column {
        Row {
            Text("Value of observedState variable is ")
            Text(
                text = observedState.value.toString(),
                color = if (observedState.value) Color.Green else Color.Red
            )
        }

        Button(onClick = { observedState.value = !observedState.value }) {
            Text("Change global state")
        }
    }

}



