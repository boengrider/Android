package com.example.textrecognition

import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.tasks.OnSuccessListener
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Call the superclass onCreate method

        val myres: Resources = resources
        Log.i("MYTAG",packageResourcePath)

        setContent {
            val image: InputImage


            try {
                image = InputImage.fromFilePath(this, Uri.parse("android.resource://com.example.textrecognition/drawable-nodpi/hashtag_senec.jpg"))
            } catch (e: IOException) {
                Log.e("MYTAG","${e.localizedMessage}")
                e.printStackTrace()
            }
            imageToExtractTextFrom(imageId = R.drawable.hashtag_senec, modifier = Modifier)
        }
    }
}

@Composable
fun imageToExtractTextFrom(imageId: Int?, modifier: Modifier = Modifier) {

    var clicks = remember { mutableStateOf(1) }
    var imageProcessingStatus = remember { mutableStateOf(false)}
    Log.i("TrackCompose", "imageToExtractTextFrom() called")
    
    extractResult(processingResult = imageProcessingStatus)
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.Blue, RectangleShape)
        .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Log.i("TrackCompose", "Box() called")
        Column(verticalArrangement = Arrangement.Top)
        {
            Log.i("TrackCompose", "Column() called")
            Image(painter = painterResource(id = R.drawable.hashtag_senec), contentDescription = null)
        }
        Button(onClick = { clicks.value++ },
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp))
        {
            Log.i("TrackCompose", "Button() called")
            Text("Extract text from this image ${clicks.value} (${imageProcessingStatus.value})", fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
@Preview
fun imageToExtractTextFromPreview() {
    imageToExtractTextFrom(imageId = null)
}

@Composable
fun extractResult(processingResult: MutableState<Boolean>) {

    val uri = Uri.parse("android.resource://com.example.textrecognition/drawable-nodpi/hashtag_senec.jpg")

    // When using Latin script library
    val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
    // Input image to process
    val image: InputImage = InputImage.fromFilePath(LocalContext.current, uri)


    val result = recognizer.process(image)

    processingResult.value = result.isSuccessful


}



