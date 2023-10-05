package com.example.cameraxsample

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.cameraxsample.databinding.ActivityGpsactivityBinding
import com.example.cameraxsample.databinding.ActivityMenuBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

class MenuActivity : AppCompatActivity() {

    private val TAG = MenuActivity::class.java.name

    private lateinit var viewBinding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        viewBinding.pictureBtn.setOnClickListener {
            val status =
                ContextCompat.checkSelfPermission(baseContext, android.Manifest.permission.CAMERA)
            if (status == PackageManager.PERMISSION_GRANTED) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                TedPermission.with(this)
                    .setPermissionListener(object : PermissionListener{
                        override fun onPermissionGranted() {
                            startActivity(Intent(baseContext, MainActivity::class.java))
                        }

                        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                            Log.e("Log","#####################onPermissionDenied#########################");
                        }

                    })
                    .setDeniedMessage("OCR 기능을 위해 허락해주세요.")
                    .setPermissions(android.Manifest.permission.CAMERA)
                    .check()
            }
        }

        viewBinding.barcodeBtn.setOnClickListener{
            val status =
                ContextCompat.checkSelfPermission(baseContext, android.Manifest.permission.CAMERA)
            if (status == PackageManager.PERMISSION_GRANTED) {
                var intent = Intent(this, BarcodeQRcodeActivity::class.java);
                startActivity(intent)
            } else {
                TedPermission.with(this)
                    .setPermissionListener(object : PermissionListener{
                        override fun onPermissionGranted() {
                            startActivity(Intent(baseContext, BarcodeQRcodeActivity::class.java))
                        }

                        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                            Log.e("Log","#####################onPermissionDenied#########################");
                        }

                    })
                    .setDeniedMessage("지도 기능을 위해 권한을 허락해주세요.")
                    .setPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    .check()
            }
        }

        viewBinding.gpsBtn.setOnClickListener { view ->

            val status =
                ContextCompat.checkSelfPermission(baseContext, android.Manifest.permission.CAMERA)
            if (status == PackageManager.PERMISSION_GRANTED) {
                var intent = Intent(this, GPSActivity::class.java);
                startActivity(intent)
            } else {
                TedPermission.with(this)
                    .setPermissionListener(object : PermissionListener{
                        override fun onPermissionGranted() {
                            startActivity(Intent(baseContext, GPSActivity::class.java))
                        }

                        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                            Log.e("Log","#####################onPermissionDenied#########################");
                        }

                    })
                    .setDeniedMessage("지도 기능을 위해 권한을 허락해주세요.")
                    .setPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    .check()
            }

        }

        viewBinding.gscanBtn.setOnClickListener{
            val status =
                ContextCompat.checkSelfPermission(baseContext, android.Manifest.permission.CAMERA)
            if (status == PackageManager.PERMISSION_GRANTED) {
                var intent = Intent(this, GScanActivity::class.java);
                startActivity(intent)
            } else {
                TedPermission.with(this)
                    .setPermissionListener(object : PermissionListener{
                        override fun onPermissionGranted() {
                            startActivity(Intent(baseContext, GScanActivity::class.java))
                        }

                        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                            Log.e("Log","#####################onPermissionDenied#########################");
                        }

                    })
                    .setDeniedMessage("지도 기능을 위해 권한을 허락해주세요.")
                    .setPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    .check()
            }


        }

    }


}