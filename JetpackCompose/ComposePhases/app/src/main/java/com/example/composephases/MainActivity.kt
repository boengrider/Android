package com.example.composephases

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composephases.ui.theme.ComposePhasesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { /** origin coordinate 0,0 **/

            /****************************************************************************
             * These two columns will overlap. Only yellow (last declared) will be visible
             */

           /**
           Column(modifier = Modifier
               .size(30.dp)
               .background(Color.Cyan)) { /** This UI node (column) has no children **/ }
           Column(modifier = Modifier
               .size(30.dp)
               .background(Color.Yellow)) { /** This UI node (column) has no children **/ }
           **/

            /****************************************************************************
             * Use contentWrap or requiredSize to center node
             */
            // CenterNode()



            GrowAndroid()

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePhasesTheme {
        Greeting("Android")
    }
}

@Composable
fun CenterNode() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)) {
        Image(painter = painterResource(id = R.drawable.pngwing_com), contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
                .size(30.dp))
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CenterNodePreview() {
    CenterNode()
}

@Composable
fun GrowAndroid() {
    Log.i("COMPOSE","GrowAndroid()")

    //Hoist the state (nodeSize) in the highest common ancestor ?
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(Color.Cyan).fillMaxSize()) {
        val nodeSize = remember { mutableStateOf(100.dp) } // State

        Log.i("COMPOSE","GrowAndroid() -> Column()")
        Image(
            painter = painterResource(id = R.drawable.pngwing_com), contentDescription = null,
            modifier = Modifier.size(nodeSize.value)
        )

        Button(onClick = { nodeSize.value = nodeSize.value + 1.dp }) {
            Log.i("COMPOSE","GrowAndroid() -> Column() -> [Grow]Button()")
            Text("Grow Android (${nodeSize.value})") // Text function reads state and therefore will be executed at every state change
        }

        //This button has no state reads. Only onClick lambda is executed. Unlike composable function Button above, which will be executed.
        Button(onClick = { nodeSize.value = 50.dp }) {
            Log.i("COMPOSE","GrowAndroid() -> Column() -> [Reset]Button()")
            Text("Reset Android")
        }
    }

}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun GrowAndroidPreview() {
    GrowAndroid()
}