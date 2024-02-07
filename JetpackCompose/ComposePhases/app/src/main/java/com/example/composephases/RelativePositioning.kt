package com.example.composephases

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloAndroid() {
    var name = rememberSaveable { mutableStateOf("") }
    var namePersistent = rememberSaveable { mutableStateOf("") }


    BoxWithConstraints(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize()) {

        Column(modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .background(Color.Magenta)
                .size(200.dp, 200.dp)) {
                Button(onClick = { /* To do */ }, shape = RectangleShape, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier.fillMaxSize()) {
                        Text(text = stringResource(R.string.magentaSquare), fontSize = 15.sp)
                }
            }

            Box(modifier = Modifier
                .background(Color.LightGray)
                .size(200.dp, 200.dp), contentAlignment = Alignment.Center) {

                Button(onClick = { /* To do */ }, shape = RectangleShape, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier.fillMaxSize()) {
                        Image(painter = painterResource(id = R.drawable.pngwing_com), contentDescription = null)
                }
            }
        }

        TextField(value = name.value, onValueChange = { name.value = it }, placeholder = { Text("Enter your name", color = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .border(0.4.dp, Color.Black, RectangleShape),
            singleLine = true, colors = TextFieldDefaults.textFieldColors(containerColor = Color.DarkGray, textColor = Color.White))

        IconButton(onClick = { name.value = "" }, modifier = Modifier.padding(start = this.minWidth - 50.dp)) {
            Icon(imageVector = Icons.Sharp.Clear, contentDescription = "Erase" )
        }





    }
}



@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HelloAndroidPreview() {
    HelloAndroid()
}
