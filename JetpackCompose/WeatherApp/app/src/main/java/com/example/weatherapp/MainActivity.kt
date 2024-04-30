package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.example.weatherapp.databinding.ActivityMainBinding
import android.net.Network
import android.net.Uri
import android.provider.Settings
import android.util.JsonReader
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
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

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        if (it[Manifest.permission.ACCESS_COARSE_LOCATION] == true && it[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
        } else {
            showRationalDialogForPermissions()
        }
    }

    /**
    override fun onResume() {
        super.onResume()
        if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            AlertDialog.Builder(this)
                .setMessage("Permission removed. Enable under Application Settings")
                .setPositiveButton("Go to settings") {
                        _, _ ->
                    try {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        e.printStackTrace()
                    }
                }
                .setNegativeButton("Cancel") {
                        dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
    }
    **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




        /**
        if(!isLocationEnabled()) Toast.makeText(this, "Your location provider is turned off", Toast.LENGTH_SHORT).show()

        getWeather(binding.weatherTw)

        binding.reloadTw.setOnClickListener {
            getWeather(binding.weatherTw)
        }
        **/

        //Not previously granted or revoked. Request permissions
        if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Log.d("debug", "Permissions not granted or revoked. Requesting permissions")
            requestPermissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                                             Manifest.permission.ACCESS_FINE_LOCATION))
        } else {
            Log.d("debug", ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION).toString())
            Log.d("debug", ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION).toString())
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

    @SuppressLint("NewApi")
    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED -> {
                //check if the permission has been denied before and show the rationale dialog
            }

            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) -> {
                showRationalDialogForPermissions()
            }

            else -> {
                requestPermissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                                                         Manifest.permission.ACCESS_FINE_LOCATION))
            }
        }
    }


    private fun showRationalDialogForPermissions() {
        AlertDialog.Builder(this)
            .setMessage("Permission removed. Enable under Application Settings")
            .setPositiveButton("Go to settings") {
                _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("Cancel") {
                dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    // Check is location provider is enabled
    private fun isLocationEnabled(): Boolean {

        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
    }
}


