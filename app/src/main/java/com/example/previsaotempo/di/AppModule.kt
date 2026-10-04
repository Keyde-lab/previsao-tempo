package com.example.previsaotempo.di

import com.example.previsaotempo.data.api.WeatherApi
import com.example.previsaotempo.repository.WeatherRepository
import com.example.previsaotempo.repository.WeatherRepositoryImpl
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import com.example.previsaotempo.viewmodel.WeatherViewModel
import org.koin.core.module.dsl.viewModel

val appModule = module {

    single {
        Json {
            ignoreUnknownKeys = true
        }
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(
                get<Json>().asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
    }

    single<WeatherApi> {
        get<Retrofit>().create(WeatherApi::class.java)
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherApi = get()
        )
    }
    viewModel {
        WeatherViewModel(
            repository = get()
        )
    }
}