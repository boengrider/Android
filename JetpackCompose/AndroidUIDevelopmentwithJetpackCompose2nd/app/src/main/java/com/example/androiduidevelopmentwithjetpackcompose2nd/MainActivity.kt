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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiduidevelopmentwithjetpackcompose2nd.ui.theme.AndroidUIDevelopmentWithJetpackCompose2ndTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            //Rectangles()
            //PredefinedLayoutDemo()
            ConstraintLayoutDemo()

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Rectangles() {
    val showRed = remember { mutableStateOf(true) }
    val showGreen = remember { mutableStateOf(true) }
    val showBlue = remember { mutableStateOf(true) }

    Surface {
        Log.i("COMPOSE","Surface()")
        Box(modifier = Modifier
            .fillMaxSize()) {

            Image(painter = painterResource(id = R.drawable.pngwing_com),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable {
                        showRed.value = true; showGreen.value = true; showBlue.value = true
                    })



            if (showRed.value) {
                //Red rectangle
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                        .clickable { showRed.value = !showRed.value }
                ) {
                    Log.i("COMPOSE","[Red]Column()")
                }
            }


            if (showGreen.value) {
                //Green rectangle
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .clickable { showGreen.value = !showGreen.value }
                        .background(Color.Green)
                ) {
                    Log.i("COMPOSE","[Green]Column()")
                }

            }


            if (showBlue.value) {
                //Blue rectangle
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                        .background(Color.Blue)
                        .drawYellowCross()
                        .clickable { showBlue.value = !showBlue.value }
                ) {
                    Log.i("COMPOSE","[Blue]Column()")
                }
            }
        }
    }
}

fun Modifier.drawYellowCross() = then (
    object : DrawModifier {
        override fun ContentDrawScope.draw() {
            drawLine(
                color = Color.Yellow,
                start = Offset(0F,0F),
                end = Offset(size.width - 1, size.height - 1),
                strokeWidth = 10F
            )

            //Draw another line
            drawLine(
                color = Color.Red,
                start = Offset(0F, size.height -1),
                end = Offset(size.width - 1, 0F),
                strokeWidth = 20F
            )
            drawContent()
        }
    }
)

fun Modifier.drawCustomRectangle() = then (
    object : DrawModifier {
        override fun ContentDrawScope.draw() {
            drawRect(
                brush = Brush.linearGradient()
            )
            drawContent()
        }
    }
)