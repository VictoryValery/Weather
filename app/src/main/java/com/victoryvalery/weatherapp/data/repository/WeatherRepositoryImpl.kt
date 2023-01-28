package com.victoryvalery.weatherapp.data.repository

import com.victoryvalery.weatherapp.data.mappers.toWeatherInfo
import com.victoryvalery.weatherapp.data.remote.WeatherApi
import com.victoryvalery.weatherapp.domain.repository.WeatherRepository
import com.victoryvalery.weatherapp.domain.util.Resource
import com.victoryvalery.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            val res = api.getWeatherData(lat, long).toWeatherInfo()
            Resource.Success(res)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }
}