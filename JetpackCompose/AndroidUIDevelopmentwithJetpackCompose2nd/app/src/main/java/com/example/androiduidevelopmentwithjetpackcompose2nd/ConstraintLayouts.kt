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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@ExperimentalComposeUiApi
@Composable
fun CheckboxWithLabel(label: String, checked: Boolean, onClicked: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.clickable { onClicked(!checked) }) {
        val (checkbox, text) = createRefs() //Create a list of references

        Checkbox(
            checked = checked,
            onCheckedChange = {
                onClicked(it)
            },
            modifier = Modifier.constrainAs(checkbox) {
               
            })

        Text(
            text = label,
            modifier = Modifier.constrainAs(text) {
                start.linkTo(checkbox.end, margin = 8.dp)
                top.linkTo(checkbox.top)
                bottom.linkTo(checkbox.bottom)
            }
        )
    }
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun ConstraintLayoutDemo() {
    var red by remember { mutableStateOf(true) }
    var green by remember { mutableStateOf(true) }
    var blue by remember { mutableStateOf(true) }
    
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        
        val (txtRed, txtGreen, txtBlue, cbRed, cbGreen, cbBlue, boxRed, boxGreen, boxBlue) = createRefs()

//Checkboxes
        CheckboxWithLabel(label = stringResource(id = R.string.cbRed),
                          checked = red,
                          onClicked = { red = it },
                          modifier = Modifier.constrainAs(cbRed) {
                              top.linkTo(parent.top)
                          })

        CheckboxWithLabel(label = stringResource(id = R.string.cbGreen),
                          checked = green,
                          onClicked = { green = it },
                          modifier = Modifier.constrainAs(cbGreen) {
                              top.linkTo(cbRed.bottom)
                          })

        CheckboxWithLabel(label = stringResource(id = R.string.cbBlue),
                          checked = blue,
                          onClicked = { blue = it },
                          modifier = Modifier.constrainAs(cbBlue) {
                              top.linkTo(cbGreen.bottom)
                          })


//Rectangles
        if(red) {
            Log.i("COMPOSE","[red]Box()")
            Box(modifier = Modifier
                .background(Color.Red)
                .constrainAs(boxRed) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(cbBlue.bottom, margin = 16.dp)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                })

            Text(text = stringResource(id = R.string.txtRed),
                fontWeight = FontWeight.W700,
                modifier = Modifier
                .constrainAs(txtRed) {
                    top.linkTo(boxRed.top, margin = 5.dp)
                    start.linkTo(boxRed.start, margin = 5.dp)

                })
        }

        if(green) {
            Log.i("COMPOSE", "[green]Box()")
            Box(modifier = Modifier
                .background(Color.Green)
                .constrainAs(boxGreen) {
                    start.linkTo(parent.start, margin = 32.dp)
                    end.linkTo(parent.end, margin = 32.dp)
                    top.linkTo(cbBlue.bottom, margin = 48.dp)
                    bottom.linkTo(parent.bottom, margin = 32.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                })

            Text(text = stringResource(id = R.string.txtGreen),
                fontWeight = FontWeight.W700,
                modifier = Modifier
                .constrainAs(txtGreen) {
                    top.linkTo(boxGreen.top, margin = 5.dp)
                    start.linkTo(boxGreen.start, margin = 5.dp)

                })
        }

        if(blue) {
            Log.i("COMPOSE", "[blue]Box()")
            Box(modifier = Modifier
                .background(Color.Blue)
                .constrainAs(boxBlue) {
                    start.linkTo(parent.start, margin = 64.dp)
                    end.linkTo(parent.end, margin = 64.dp)
                    top.linkTo(cbBlue.bottom, margin = 80.dp)
                    bottom.linkTo(parent.bottom, margin = 64.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                })

            Text(text = stringResource(id = R.string.txtRed),
                fontWeight = FontWeight.W700,
                modifier = Modifier
                .constrainAs(txtBlue) {
                    top.linkTo(boxBlue.top, margin = 5.dp)
                    start.linkTo(boxBlue.start, margin = 5.dp)

                })
        }

    }
}

