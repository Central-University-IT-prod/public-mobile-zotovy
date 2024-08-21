package dev.zotov.prod_app

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dev.zotov.prod_app.ui.theme.Prod_appTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    companion object {
        const val PERMISSION_LOCATION_REQUEST_CODE = 10002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDependencies()

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                PERMISSION_LOCATION_REQUEST_CODE
            )
        } else {
            getLocation()
        }

        setContent {
            Prod_appTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Router()
                }
            }
        }
    }

    private fun setupDependencies() {
        DI.setup(
            userSharedPreferences = getSharedPreferences("Users", Context.MODE_PRIVATE),
            tasksSharedPreferences = getSharedPreferences("Tasks", Context.MODE_PRIVATE),
            launchUri = this::launchUri,
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_LOCATION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getLocation()
                } else {
                    DI.homeViewModel.handleUnknownLocation()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        try {
            val fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this@MainActivity)

            val locationRequest =
                LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 60000).apply {
                    setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                    setWaitForAccurateLocation(true)
                }.build()

            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult) {
                        super.onLocationResult(p0)
                        try {
                            if (p0.locations.size > 0) {
                                val location = p0.locations.first()
                                val geocoder =
                                    Geocoder(this@MainActivity, Locale.forLanguageTag("ru"))
                                val addresses =
                                    geocoder.getFromLocation(
                                        location.latitude,
                                        location.longitude,
                                        1
                                    )
                                if (!addresses.isNullOrEmpty()) {
                                    val locationName = addresses[0].locality
                                    handleLocationChange(location, locationName)
                                } else {
                                    handleLocationChange(location, null)
                                }
                            }
                            fusedLocationProviderClient.removeLocationUpdates(this)
                        } catch (e: Throwable) {
                            handleUnknownLocation()
                        }
                    }
                },
                Looper.getMainLooper(),
            )
        } catch (e: Throwable) {
            handleUnknownLocation()
        }
    }

    private fun launchUri(source: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(source))
            startActivity(intent)
        } catch (e: Throwable) {
            Log.e("launchUri", "Failed to launch string $source", e)
        }
    }

    private fun handleUnknownLocation() {
        DI.homeViewModel.handleUnknownLocation()
        DI.mapViewModel.setUnknownLocation()
    }

    private fun handleLocationChange(location: Location, locationName: String?) {
        DI.homeViewModel.handleLocationChange(
            location.latitude,
            location.longitude,
            locationName,
        )
        DI.mapViewModel.setLocation(
            location.latitude,
            location.longitude,
        )
    }
}