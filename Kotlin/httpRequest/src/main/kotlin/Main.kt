package org.example

import java.net.URL

fun main() {
    println(URL("https://zive.aktuality.sk/").readText()[10].toString())
}

