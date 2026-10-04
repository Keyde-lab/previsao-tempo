package com.example.previsaotempo.viewmodel

import com.example.previsaotempo.data.model.ForecastItem

sealed class WeatherUIState {

    object Loading : WeatherUIState()

    data class Success(
        val forecast: List<ForecastItem>
    ) : WeatherUIState()

    data class Error(
        val message: String
    ) : WeatherUIState()
}