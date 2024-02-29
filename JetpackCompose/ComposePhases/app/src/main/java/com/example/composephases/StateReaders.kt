package com.example.composephases

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateReader() {

    val composableScope = rememberCoroutineScope()
    var charCount by remember { mutableStateOf(0)}
    var selection by remember { mutableStateOf(0) }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center,
           modifier = Modifier.fillMaxSize()) {

        Log.i("COMPSOE","Column() called")
        //State reader
        Text(text = selection.toString(),
             color = MaterialTheme.colorScheme.onPrimary,
             style = MaterialTheme.typography.headlineMedium,
             modifier = Modifier
                 .background(MaterialTheme.colorScheme.primary)
                 .padding(10.dp))



        Button(onClick = { selection = 1 }) {
            Log.i("COMPOSE","Button() [1] called")
            Text(text = "1")
        }

        Button(onClick = { selection = 2 }) {
            Log.i("COMPOSE","Button() [2] called")
            Text(text = "2")
        }

        Button(onClick = { composableScope.launch { charCount = URL("https://zive.aktuality.sk/").readBytes().size } } ) {
            Text("Send URL request")
        }

        Text(text = charCount.toString())



    }

}

suspend fun sendHttpRequest() {
    coroutineScope {
        var charCount: Int
        launch {
            charCount = URL("https://zive.aktuality.sk/").readBytes().size
        }
    }
}
@Composable
@Preview(showSystemUi = true)
fun SateReaderPreview() {
    StateReader()
}