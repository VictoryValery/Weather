package com.victoryvalery.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

private const val URI = "v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl"

interface WeatherApi {

    @GET(URI)
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherDto

}