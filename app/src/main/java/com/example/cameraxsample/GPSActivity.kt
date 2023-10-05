package com.example.cameraxsample

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.example.cameraxsample.databinding.ActivityGpsactivityBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons

class GPSActivity : AppCompatActivity(), OnMapReadyCallback {
    private var LOCATION_PERMISSION = 1004
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private val marker = Marker()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val PERMISSION = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION ,android.Manifest.permission.ACCESS_COARSE_LOCATION )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gpsactivity)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationSource = FusedLocationSource(this@GPSActivity , LOCATION_PERMISSION)
        val fragmentManager: FragmentManager = supportFragmentManager
        var mapFragment: MapFragment? = fragmentManager.findFragmentById(R.id.map) as MapFragment?
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance()
            fragmentManager.beginTransaction().add(R.id.map, mapFragment).commit()
        }

        mapFragment!!.getMapAsync(this)

    }

    @UiThread
    override fun onMapReady(map: NaverMap) {

        naverMap = map
//        naverMap.maxZoom =18.0
//        naverMap.minZoom =5.0


//        카메라 설정
        val cameraPosition = CameraPosition(
            LatLng(37.5666102, 126.9783881), // 대상 지점
            16.0, // 줌 레벨
            0.0, // 기울임 각도
            0.0 // 베어링 각도
        )
        naverMap.cameraPosition = cameraPosition

        naverMap.addOnCameraChangeListener { reason, animated ->
            // 마커 포지션
//            marker.position = LatLng(naverMap.cameraPosition.target.latitude, naverMap.cameraPosition.target.longitude) }

//        naverMap.addOnCameraIdleListener {
//            // 현재 보이는 네이버맵의 정중앙 가운데로 마커
//            marker.map = naverMap
//            marker.icon = MarkerIcons.BLACK
//            marker.iconTintColor = Color.BLUE
//
        }
        naverMap.locationSource = locationSource
        ActivityCompat.requestPermissions(this, PERMISSION, LOCATION_PERMISSION)
    }



    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when {
            requestCode != LOCATION_PERMISSION -> {
                return
            }
            else -> {
                when {
                    locationSource.onRequestPermissionsResult(requestCode,permissions,grantResults) -> {
                        if (!locationSource.isActivated){
                            naverMap.locationTrackingMode = LocationTrackingMode.None
                        }else{
                            naverMap.locationTrackingMode = LocationTrackingMode.Follow
                        }
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}