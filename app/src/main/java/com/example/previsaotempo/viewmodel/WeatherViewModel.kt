package com.example.previsaotempo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.previsaotempo.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    // Estado atual da interface
    private val _uiState =
        MutableStateFlow<WeatherUIState>(
            WeatherUIState.Loading
        )

    // Estado que será observado pela tela
    val uiState: StateFlow<WeatherUIState> =
        _uiState.asStateFlow()

    // Busca a previsão do tempo
    fun getForecast(
        city: String,
        apiKey: String
    ) {

        viewModelScope.launch {

            // Informa à tela que os dados estão sendo carregados
            _uiState.value =
                WeatherUIState.Loading

            try {

                // Solicita os dados ao Repository
                val response =
                    repository.getForecast(
                        city = city,
                        apiKey = apiKey
                    )

                // Se a consulta funcionar,
                // envia os dados para a tela
                _uiState.value =
                    WeatherUIState.Success(
                        response.list
                    )

            } catch (e: Exception) {

                // Se ocorrer algum erro,
                // informa o estado de erro à tela
                _uiState.value =
                    WeatherUIState.Error(
                        "Não foi possível carregar a previsão."
                    )
            }
        }
    }
}