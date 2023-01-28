package com.victoryvalery.weatherapp.presentation

import android.Manifest
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.victoryvalery.weatherapp.presentation.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionsLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionsLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }

        permissionsLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )

        var textColor = LigthTextBlue
        var backgroundApp = White
        var backgroundCard = LigthBlue

        setContent {
            WeatherAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundApp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        WeatherCard(
                            state = viewModel.state,
                            backgroundColor = backgroundCard,
                            textColor = textColor
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(
                            state = viewModel.state,
                            textColor = textColor,
                            backgroundItemColor = backgroundCard
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherWeekForecast(
                            state = viewModel.state,
                            textColor = textColor,
                        )
                    }
                    if (viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            color = backgroundCard,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                    viewModel.state.error?.let {
                        Text(
                            text = it,
                            color = textColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}