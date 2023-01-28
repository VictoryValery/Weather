package com.victoryvalery.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victoryvalery.weatherapp.domain.weather.WeatherData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun WeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    backgroundItemColor: Color,
    textColor: Color = Color.White
) {
    Card(
        backgroundColor = backgroundItemColor,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .padding(horizontal = 4.dp)
            .width(60.dp)
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val formattedTime = remember(weatherData) {
                weatherData.time.format(
                    DateTimeFormatter.ofPattern("HH:mm")
                )
            }
            val formattedTimeText = if (
                weatherData.time.hour == LocalDateTime.now().hour
            )
                "now"
            else
                formattedTime
            Text(
                text = formattedTimeText,
                fontSize = 16.sp,
                color = textColor
            )
            Image(
                painter = painterResource(id = weatherData.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier.height(40.dp)
            )
            Text(
                text = "${weatherData.temperatureCelsius}°С",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}