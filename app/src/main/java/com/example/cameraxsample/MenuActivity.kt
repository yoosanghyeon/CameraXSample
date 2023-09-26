package com.example.cameraxsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cameraxsample.databinding.ActivityGpsactivityBinding
import com.example.cameraxsample.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.gpsBtn.setOnClickListener { view ->
            startActivity(Intent(this, GPSActivity::class.java))
        }

        viewBinding.pictureBtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}