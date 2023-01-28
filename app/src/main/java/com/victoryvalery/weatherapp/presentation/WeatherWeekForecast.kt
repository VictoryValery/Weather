package com.victoryvalery.weatherapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime

@Composable
fun WeatherWeekForecast(
    state: WeatherState,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        state.weatherInfo?.weatherDataPerDay
            ?.filterNot { it.key == 0 }
            ?.forEach { weatherMap ->
                val cardDayWeek =
                    if (weatherMap.key == 1)
                        "Tomorrow"
                    else
                        LocalDateTime.now().plusDays(weatherMap.key.toLong())
                            .dayOfWeek
                            .toString()
                            .lowercase()
                            .replaceFirstChar(Char::titlecase)
                Text(
                    text = cardDayWeek,
                    color = textColor,
                    fontSize = 20.sp,
                )
                WeatherWeekForecastCard(
                    weatherDayValue = weatherMap.value,
                    weatherDayKey = weatherMap.key,
                    textColor = textColor
                )
            }
    }

}
