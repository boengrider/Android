package com.example.textrecognition

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException
import java.lang.NullPointerException


class MainActivity : ComponentActivity() {


    private val imageUri: Uri = Uri.parse("android.resource://com.example.textrecognition/" + R.drawable.hashtag_senec)
    private var inputImage: InputImage? = null







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Call the superclass onCreate method

        try {
            inputImage = InputImage.fromFilePath(this,imageUri)
            Log.i("MYTAG","Image loaded successfully")

        } catch (e:IOException) {
            Log.e("MYTAG",e.toString())
            this.finish()
        }

        if (inputImage == null) Log.e("MYTAG","Image is null before calling setContent()")

        setContent {
            Log.i("MYTAG",imageUri.toString())
            var jobDone = remember { mutableStateOf(false) }
            var jobSuccessful = remember { mutableStateOf(false) }

            val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            val task: Task<Text> = recognizer.process(inputImage!!)
            task.addOnSuccessListener { jobDone.value = true; jobSuccessful.value = true; Log.i("MYTAG","Image processing successful") }
            task.addOnFailureListener { e -> jobDone.value = true; jobSuccessful.value = false; Log.e("MYTAG","Image processing failed ${e.cause}")}

            
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {

                if (jobDone.value) {
                    if (jobSuccessful.value) {
                        Text(
                            text = "Job is done and successful",
                            color = Color.Green,
                            fontSize = 40.sp
                        )
                    } else {
                        Text(
                            text = "Job is done but failed",
                            color = Color.Red,
                            fontSize = 40.sp
                        )

                    }
                } else {
                    Text("Job is not yet done", color = Color.Red, fontSize = 30.sp)
                }
            }
        }
    }
}




