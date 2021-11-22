package com.example.kmmdemo

expect class WeatherApi {
    suspend fun fetchData(): String?
}
