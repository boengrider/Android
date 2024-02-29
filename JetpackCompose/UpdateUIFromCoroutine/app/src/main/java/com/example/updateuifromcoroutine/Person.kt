package com.example.updateuifromcoroutine

class Person(val age: Int = 99, val name: String = "Jon Doe") {
}

fun Person.getAge(): Int {
    return this.age
}