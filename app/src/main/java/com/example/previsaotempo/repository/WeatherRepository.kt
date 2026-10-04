package com.example.previsaotempo.repository

import com.example.previsaotempo.data.model.WeatherResponse

interface WeatherRepository {

    suspend fun getForecast(
        city: String,
        apiKey: String
    ): WeatherResponse
}