package com.example.kmmdemo

import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*

actual class WeatherApi {
    actual suspend fun fetchData(): String? {

        try {
            val client = HttpClient(Android)
            val response = client.get<String>(ApiUrl.WEATHER_FORECAST)
            return response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun convertJson(jsonString: String): WeatherData {
        val gson = Gson()
        return gson.fromJson(jsonString, WeatherData::class.java)
    }
}
