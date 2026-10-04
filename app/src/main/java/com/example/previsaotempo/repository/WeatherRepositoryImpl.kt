package com.example.previsaotempo.repository

import com.example.previsaotempo.data.api.WeatherApi
import com.example.previsaotempo.data.model.WeatherResponse

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getForecast(
        city: String,
        apiKey: String
    ): WeatherResponse {

        return weatherApi.getForecast(
            city = city,
            apiKey = apiKey
        )
    }
}