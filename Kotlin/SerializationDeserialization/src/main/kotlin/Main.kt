package org.example

import com.google.gson.JsonParser
import java.net.HttpURLConnection
import java.net.URL

fun main() {


    val url = URL(Constants.apiEndpoint + "?lat=48.21&lon=17.40&appid=${Constants.apiKey}")
    val httpUrlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
    httpUrlConnection.setRequestProperty("Accept","application/json")
    httpUrlConnection.requestMethod = "GET"

    println(httpUrlConnection.responseCode)

    try {
        val responseCode = httpUrlConnection.responseCode

        if (responseCode == HttpURLConnection.HTTP_OK) {
            val response = httpUrlConnection.inputStream.bufferedReader()

            val payload = JsonParser.parseReader(response) // Pass Reader to the parser
            val jObject = payload.asJsonObject             // Convert to JSON tree
            println(jObject.toString())
            val weather = jObject.get("weather").asJsonArray[0]
            println(weather.asJsonObject.get("description"))
        }
    } catch(e:Exception) {
        println("Excepction occured: ${e.message}")
    } finally {
        httpUrlConnection.disconnect()
    }
}
