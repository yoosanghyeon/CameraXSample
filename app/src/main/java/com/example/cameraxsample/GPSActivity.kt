package com.example.cameraxsample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.cameraxsample.databinding.ActivityGpsactivityBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

class GPSActivity : AppCompatActivity(), OnMapReadyCallback {

    val TAG = GPSActivity::class.java.name

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var viewBinding: ActivityGpsactivityBinding

    private lateinit var naverMap: NaverMap


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

                naverMap.moveCamera(cameraUpdate)
            }

    }

    override fun onMapReady(p0: NaverMap) {
        Log.e(TAG, "OnmapReady")
        naverMap = p0

//        Handler().postDelayed({
//            val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.4816836, 126.8848364))
//                .animate(CameraAnimation.Easing)
//
//            naverMap.moveCamera(cameraUpdate)
//        }, 100)
    }


}