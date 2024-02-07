package com.example.composephases

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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

//This will not survive a configuration change e.g rotation or language change
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayNameDoesNotSurviveConfigChange() {
    val name = remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)) {

        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .background(Color.Red), placeholder = { Text("Please etner your name") })

        Text(text = stringResource(id = R.string.greetings), modifier = Modifier.padding(start = 10.dp))
    }

}

@Composable
@Preview
fun DisplayNameDoesNotSurviveConfigChangePreview() {
    DisplayNameDoesNotSurviveConfigChange()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayNameDoesSurviveConfigChange() {
    val name = rememberSaveable { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)) {

        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .background(Color.Red), placeholder = { Text("Please enter your name") })

        Text(text = stringResource(id = R.string.greetings), modifier = Modifier.padding(start = 10.dp))

    }

}

@Composable
@Preview
fun DisplayNameDoesSurviveConfigChangePreview() {
    DisplayNameDoesSurviveConfigChange()
}
