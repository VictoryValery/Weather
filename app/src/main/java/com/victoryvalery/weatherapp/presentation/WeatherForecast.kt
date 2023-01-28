package com.victoryvalery.weatherapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime

@Composable
fun WeatherForecast(
    state: WeatherState,
    backgroundItemColor: Color,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = textColor
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {
                    items(
                       it.filter { weatherData ->
                           weatherData.time.hour >= LocalDateTime.now().hour
                       }
                    ) { weatherData ->
                        WeatherDisplay(
                            weatherData = weatherData,
                            modifier = Modifier
                                .height(100.dp)
                                .padding(horizontal = 4.dp)
                            ,
                            textColor = textColor,
                            backgroundItemColor = backgroundItemColor
                        )
                    }
                }
            )
        }

    }
}