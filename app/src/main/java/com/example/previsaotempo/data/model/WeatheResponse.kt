package com.example.previsaotempo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val list: List<ForecastItem>
)

@Serializable
data class ForecastItem(

    @SerialName("dt_txt")
    val dateTime: String,

    val main: MainWeather,

    val weather: List<WeatherDescription>
)

@Serializable
data class MainWeather(
    val temp: Double
)

@Serializable
data class WeatherDescription(
    val description: String
)