package com.victoryvalery.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victoryvalery.weatherapp.domain.weather.WeatherData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun WeatherWeekForecastCard(
    modifier: Modifier = Modifier,
    weatherDayValue: List<WeatherData>,
    weatherDayKey: Int,
    textColor: Color = Color.White
) {
    Card(
        modifier = modifier
            .clickable { }
            .padding(2.dp),
        elevation = 1.dp
    ) {

        val cardDate = LocalDateTime.now().plusDays(weatherDayKey.toLong()).format(
            DateTimeFormatter.ofPattern("dd MMM", Locale.US)
        ).toString()

        val day = weatherDayValue[weatherDayValue.size / 2]

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent, RoundedCornerShape(4.dp))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = cardDate,
                color = Color.Gray,
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.padding(horizontal = 16.dp))
            Text(
                text = day.temperatureCelsius.toString() + "°",
                fontSize = 25.sp,
                color = textColor
            )
            Spacer(modifier = Modifier.padding(horizontal = 16.dp))
            imageContainer(day.weatherType.iconRes)
        }
    }
}

@Composable
fun imageContainer(
    iconRes: Int
) {
    Box(
        Modifier
            .width(40.dp)
            .height(40.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.height(40.dp)
        )
    }
}