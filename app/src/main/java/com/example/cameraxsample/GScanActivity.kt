package com.example.cameraxsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cameraxsample.databinding.ActivityGscanBinding

class GScanActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityGscanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityGscanBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


    }
}