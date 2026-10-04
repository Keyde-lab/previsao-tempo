package com.example.previsaotempo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.previsaotempo.ui.theme.PrevisaoTempoTheme
import com.example.previsaotempo.view.WeatherScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PrevisaoTempoTheme {
                WeatherScreen()
            }
        }
    }
}