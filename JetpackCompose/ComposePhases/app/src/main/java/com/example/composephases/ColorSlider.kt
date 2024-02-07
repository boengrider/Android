package com.example.composephases

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColorPickerB(color: MutableState<Color>, sliderRed: Color = Color.Red, sliderGreen: Color = Color.Green, sliderBlue: Color = Color.Blue) {
    val red = color.value.red
    val green = color.value.green
    val blue = color.value.blue
    Column {
        Slider(
            value = red,
            onValueChange = { color.value = Color(it, green, blue) },
            modifier = Modifier.background(sliderRed))
        Slider(
            value = green,
            onValueChange = { color.value = Color(red, it, blue) },
            modifier = Modifier.background(sliderGreen))
        Slider(
            value = blue,
            onValueChange = { color.value = Color(red, green, it) },
            modifier = Modifier.background(sliderBlue))
    }
}

@Composable
fun ColorPicker(color: MutableState<Color>, sliderRed: Color = Color.Red, sliderGreen: Color = Color.Green, sliderBlue: Color = Color.Blue) {
    val red = color.value.red
    val green = color.value.green
    val blue = color.value.blue
    Column {
        Slider(
            value = red,
            onValueChange = { color.value = Color(it, green,
                blue) },
            modifier = Modifier.background(sliderRed))
        Slider(
            value = green,
            onValueChange = { color.value = Color(red, it,
                blue) },
            modifier = Modifier.background(sliderGreen))
        Slider(
            value = blue,
            onValueChange = { color.value = Color(red, green,
                it) },
            modifier = Modifier.background(sliderBlue))
    }
}

@Composable
fun ColorPicker(color: MutableState<Color>, sliderColors: List<Color>) {
    val red = color.value.red
    val green = color.value.green
    val blue = color.value.blue
    Column {
        Slider(
            value = red,
            onValueChange = { color.value = Color(it, green,
                blue) },
            modifier = Modifier.background(sliderColors[0]))
        Slider(
            value = green,
            onValueChange = { color.value = Color(red, it,
                blue) },
            modifier = Modifier.background(sliderColors[1]))
        Slider(
            value = blue,
            onValueChange = { color.value = Color(red, green,
                it) },
            modifier = Modifier.background(sliderColors[2]))
    }
}

