package com.example.kmmdemo

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!".uppercase()
    }
}