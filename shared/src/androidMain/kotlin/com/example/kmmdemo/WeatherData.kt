package com.example.kmmdemo

data class WeatherData(
    val records: Records = Records(),
    val result: Result = Result(),
    val success: String = ""
) {
    data class Records(
        val locations: List<Location> = listOf()
    ) {
        data class Location(
            val dataid: String = "",
            val datasetDescription: String = "",
            val location: List<Location> = listOf(),
            val locationsName: String = ""
        ) {
            data class Location(
                val geocode: String = "",
                val lat: String = "",
                val locationName: String = "",
                val lon: String = "",
                val weatherElement: List<WeatherElement> = listOf()
            ) {
                data class WeatherElement(
                    val description: String = "",
                    val elementName: String = "",
                    val time: List<Time> = listOf()
                ) {
                    data class Time(
                        val elementValue: List<ElementValue> = listOf(),
                        val endTime: String = "",
                        val startTime: String = ""
                    ) {
                        data class ElementValue(
                            val measures: String = "",
                            val value: String = ""
                        )
                    }
                }
            }
        }
    }

    data class Result(
        val fields: List<Field> = listOf()
    ) {
        data class Field(
            val id: String = "",
            val type: String = ""
        )
    }
}