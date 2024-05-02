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

            val weatherMessage: String = jObject.get("weather").asJsonArray.get(0).asJsonObject.get("description").toString()
            println(weatherMessage)
        }
    } catch(e:Exception) {
        println("Excepction occured: ${e.message}")
    } finally {
        httpUrlConnection.disconnect()
    }

    val functionList: MutableMap<String, (Int) -> Unit> = mutableMapOf(
        Pair("square") {
            println(it * it)
        }
    )

    functionList["square"]!!(2)

    functionList["increment"] = {
        println(it + 1)
    }

    functionList["increment"]!!(20)


}
