package com.example.cameraxsample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.cameraxsample.databinding.ActivityGpsactivityBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker

class GPSActivity : AppCompatActivity(), OnMapReadyCallback {

    val TAG = GPSActivity::class.java.name

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var viewBinding: ActivityGpsactivityBinding

    private var naverMap: NaverMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityGpsactivityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_fragment, it).commit()
            }

        mapFragment.getMapAsync(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener {
            val cameraUpdate = CameraUpdate.scrollTo(LatLng(it.latitude, it.longitude))
                .animate(CameraAnimation.Easing)

            naverMap?.moveCamera(cameraUpdate)
            val marker = Marker()
            marker.position = LatLng(it.latitude, it.longitude)
            marker.map = naverMap
        }
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult == null) {
                    return
                }
                for (location in locationResult.locations) {
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        val cameraUpdate = CameraUpdate.scrollTo(LatLng(latitude, longitude))
                            .animate(CameraAnimation.Easing)
                        Log.e(TAG, "lat:: $latitude lon:: $longitude")
                        naverMap?.moveCamera(cameraUpdate)
                    }
                }
            }
        }

        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 20 * 1000
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        );


    }

    override fun onMapReady(p0: NaverMap) {
        Log.e(TAG, "OnmapReady")
        p0.extent = LatLngBounds(LatLng(31.43, 122.37), LatLng(44.35, 132.0))
        p0.minZoom = 5.0
        p0.maxZoom = 18.0
        naverMap = p0

    }


}