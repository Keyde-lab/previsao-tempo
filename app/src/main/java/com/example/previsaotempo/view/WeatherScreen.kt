package com.example.previsaotempo.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.previsaotempo.BuildConfig
import com.example.previsaotempo.viewmodel.WeatherUIState
import com.example.previsaotempo.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel
import java.text.Normalizer

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = koinViewModel()
) {

    var city by remember {
        mutableStateOf("Goiânia")
    }

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Previsão do Tempo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = city,
            onValueChange = {
                city = it
            },
            label = {
                Text("Cidade")
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.DarkGray,
                cursorColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Button(
            onClick = {

                if (city.isNotBlank()) {

                    val cidadeParaBusca =
                        prepararCidade(city)

                    viewModel.getForecast(
                        city = cidadeParaBusca,
                        apiKey = BuildConfig.OPENWEATHER_API_KEY
                    )
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {

            Text("Buscar")
        }

        when (val state = uiState) {

            is WeatherUIState.Loading -> {

                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(32.dp)
                )
            }

            is WeatherUIState.Success -> {

                LazyColumn(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    items(state.forecast) { item ->

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            )
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {

                                Text(
                                    text = formatDateTime(
                                        item.dateTime
                                    ),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )

                                Text(
                                    text = "${item.main.temp} °C",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(
                                        top = 6.dp
                                    )
                                )

                                Text(
                                    text = item.weather
                                        .firstOrNull()
                                        ?.description
                                        ?: "Sem descrição",
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(
                                        top = 4.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }

            is WeatherUIState.Error -> {

                Column(
                    modifier = Modifier.padding(top = 24.dp)
                ) {

                    Text(
                        text = "Não foi possível carregar a previsão."
                    )

                    Button(
                        onClick = {

                            if (city.isNotBlank()) {

                                val cidadeParaBusca =
                                    prepararCidade(city)

                                viewModel.getForecast(
                                    city = cidadeParaBusca,
                                    apiKey =
                                        BuildConfig.OPENWEATHER_API_KEY
                                )
                            }
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {

                        Text("Tentar Novamente")
                    }
                }
            }
        }
    }
}


// Prepara o nome da cidade para enviar à API
fun prepararCidade(city: String): String {

    // Remove espaços extras
    val cidadeLimpa = city.trim()

    // Remove os acentos
    val cidadeSemAcento = Normalizer
        .normalize(
            cidadeLimpa,
            Normalizer.Form.NFD
        )
        .replace(
            Regex("\\p{InCombiningDiacriticalMarks}+"),
            ""
        )

    // Acrescenta o código do Brasil
    return "$cidadeSemAcento,BR"
}


// Formata a data recebida da API
fun formatDateTime(
    dateTime: String
): String {

    return try {

        val partes = dateTime.split(" ")

        val data = partes[0].split("-")

        val hora = partes[1].substring(
            0,
            5
        )

        "${data[2]}/${data[1]} às $hora"

    } catch (e: Exception) {

        dateTime
    }
}