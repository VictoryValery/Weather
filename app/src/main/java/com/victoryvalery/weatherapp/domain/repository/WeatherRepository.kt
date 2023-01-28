package com.victoryvalery.weatherapp.domain.repository

import com.victoryvalery.weatherapp.domain.util.Resource
import com.victoryvalery.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}