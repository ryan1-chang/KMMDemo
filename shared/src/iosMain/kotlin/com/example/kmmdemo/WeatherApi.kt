package com.example.kmmdemo

actual class WeatherApi {
    actual suspend fun fetchData(): String? {
        return null
    }
}
