package com.victoryvalery.weatherapp.data.mappers

import com.victoryvalery.weatherapp.data.remote.WeatherDataDto
import com.victoryvalery.weatherapp.data.remote.WeatherDto
import com.victoryvalery.weatherapp.domain.weather.WeatherData
import com.victoryvalery.weatherapp.domain.weather.WeatherInfo
import com.victoryvalery.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDto.toWeatherInfo() : WeatherInfo {
    val wDM = weatherData.toWeatherDataMap()
    return WeatherInfo(
        weatherDataPerDay = wDM,
        currentWeatherData = wDM[0]?.find {
            it.time.hour == LocalDateTime.now().hour
        }
    )
}

private data class WeatherDataInd(
    val dayIndex: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        WeatherDataInd(
            dayIndex = index,
            data = WeatherData(
                temperatureCelsius = temperatures[index],
                humidity = humidities[index],
                pressure = pressures[index],
                windSpeed = windSpeeds[index],
                weatherType = WeatherType.fromWMO(weatherCodes[index]),
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME)
            )
        )
    }.groupBy {
        it.dayIndex / 24
    }.mapValues {
        it.value.map { it.data }
    }
}