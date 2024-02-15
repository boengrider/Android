package com.example.androiduidevelopmentwithjetpackcompose2nd

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//Predefined layouts:
// Horizontal (along X axis) Implemented by Row()
// Vertical   (along Y axis) Implemented by Column()
// Stacked    (along Z axis) Implemented by Box() or BoxWithConstraints()

//We will use the predefined layouts and combine them to create a simple ui example


//Create a checkbox with a label using predefined Row layout, and Checkbox and Text elements
//By combining these predefined layouts, we can create a more complex layouts
//UI tree is deeper tho, since we nest these layouts
@Composable
fun CheckboxWithLabel(label: String, checked: Boolean, onClicked: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = { onClicked(it) })
        Text(text = label, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PredefinedLayoutDemo() {
    //State is hoisted in the PredefinedLayoutDemo function
    val showRed = remember { mutableStateOf(true)}
    val showGreen = remember { mutableStateOf(true)}
    val showBlue = remember { mutableStateOf(true)}

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        //Red checkbox
        CheckboxWithLabel(
            label = "Red",
            checked = showRed.value,
            onClicked = { showRed.value = it })
        //Green checkbox
        CheckboxWithLabel(
            label = "Green",
            checked = showGreen.value,
            onClicked = { showGreen.value = it })
        //Blue checkbox
        CheckboxWithLabel(
            label = "Blue",
            checked = showBlue.value,
            onClicked = { showBlue.value = it })


        //Now combine Box layout with three nested boxes
        //Main box
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .background(Color.Magenta)
        ) {

            if (showRed.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                )
            }


            if (showGreen.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                        .background(Color.Green)
                )
            }


            if (showBlue.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(64.dp)
                        .background(Color.Blue)
                )
            }
        }
    }
    
}

fun checkBoxClicked(checkBoxState: Boolean) {
    Log.i("COMPOSE","Checkbox is checked -> $checkBoxState")
}
