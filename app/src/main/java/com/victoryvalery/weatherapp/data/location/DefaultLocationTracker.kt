package com.victoryvalery.weatherapp.data.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.victoryvalery.weatherapp.domain.location.LocationTracker
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? {
        if (!permissionsGained())
            return null
        else
            return suspendCancellableCoroutine { continuation ->
                locationClient.lastLocation.apply {
                    if (isComplete) {
                        if (isSuccessful)
                            continuation.resume(result)
                        else
                            continuation.resume(null)
                        return@suspendCancellableCoroutine
                    }
                    addOnSuccessListener {
                        continuation.resume(it)
                    }
                    addOnFailureListener {
                        continuation.resume(null)
                    }
                    addOnCanceledListener {
                        continuation.cancel()
                    }
                }
            }
    }

    private fun permissionsGained(): Boolean {
        val fine = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarse = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        return fine && coarse && gpsEnabled
    }

}