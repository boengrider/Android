package com.example.textrecognition

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.textrecognition.ui.theme.TextRecognitionTheme
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Call the superclass onCreate method


        setContent {
            imageToExtractTextFrom(imageId = R.drawable.hashtag_senec, modifier = Modifier)
        }
    }
}

@Composable
fun imageToExtractTextFrom(imageId: Int?, modifier: Modifier = Modifier) {
    var clicks = remember { mutableStateOf(1) }
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.Blue, RectangleShape)
        .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(verticalArrangement = Arrangement.Top)
        {
            Image(painter = painterResource(id = R.drawable.hashtag_senec), contentDescription = null)
        }
        Button(onClick = {  },
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp))
        {
            Text("Extract text from this image ${clicks.value}", fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
@Preview
fun imageToExtractTextFromPreview() {
    imageToExtractTextFrom(imageId = null)
}