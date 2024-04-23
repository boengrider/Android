package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.example.weatherapp.databinding.ActivityMainBinding
import android.net.Network
import android.util.JsonReader
import android.util.Log
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val json = "{ \"name\": \"John\", \"age\": 30 }"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        parseJson(json)

    }
}

@Serializable
data class User(val name: String, val age: Int)

fun parseJson(json: String) {
    val user = Json.decodeFromString<User>(json)
    Log.d("JSON", user.toString())
}



/**
fun getWeatherForecast() {
    Executors.newSingleThreadExecutor().execute {
        var httpURLConnection: HttpURLConnection? = null

        //Try-catch-finally block
        try {
            var url = URL("https://api.openweathermap.org/data/2.5/weather?lat=35&lon139&appid=" +
                    "4899f96f6d4f7d7046833b9d879f9c91")

            httpURLConnection = url.openConnection() as HttpURLConnection

            httpURLConnection.setRequestProperty("Accept","application/json")
            httpURLConnection.requestMethod = "GET"
            httpURLConnection.doInput = true
            httpURLConnection.doOutput = false
            //Connect ?
            httpURLConnection.connect()

            val responseCode = httpURLConnection.responseCode


            if(responseCode == HttpURLConnection.HTTP_OK) {
                //read json
                val response = httpURLConnection.inputStream.bufferedReader().use { it.readText() }

                //parse json
                val json = Json.decodeFromString<Weather>(response)
            }
        }
    }
}
        **/