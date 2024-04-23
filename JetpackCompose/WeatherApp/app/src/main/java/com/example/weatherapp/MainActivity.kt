package com.example.weatherapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.example.weatherapp.databinding.ActivityMainBinding
import android.net.Network
import android.util.JsonReader
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonParser
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getWeather(binding.weatherTw)

        binding.reloadTw.setOnClickListener {
            getWeather(binding.weatherTw)
        }
    }


    private fun getWeather(weatherTw:TextView) {
        Executors.newSingleThreadExecutor().execute {
            val url = URL(Constants.apiEndpoint + "?lat=48.21&lon=17.40&appid=${Constants.apiKey}")
            val httpUrlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            httpUrlConnection.setRequestProperty("Accept", "application/json")
            httpUrlConnection.requestMethod = "GET"

            println(httpUrlConnection.responseCode)

            try {
                val responseCode = httpUrlConnection.responseCode

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = httpUrlConnection.inputStream.bufferedReader()

                    val payload = JsonParser.parseReader(response) // Pass Reader to the parser
                    val jObject = payload.asJsonObject             // Convert to JSON tree

                    weatherTw.text =
                        jObject.get("weather").asJsonArray.get(0).asJsonObject.get("description").asString
                }
            } catch (e: Exception) {
                weatherTw.text = e.message.toString()
            } finally {
                httpUrlConnection.disconnect()
            }

        }
    }
}


