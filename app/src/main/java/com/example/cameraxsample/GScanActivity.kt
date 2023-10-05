package com.example.cameraxsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cameraxsample.databinding.ActivityGscanBinding
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

class GScanActivity : AppCompatActivity() {

    val TAG = GScanActivity::class.java.name

    lateinit var viewBinding: ActivityGscanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityGscanBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val options = GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_QR_CODE,
                Barcode.FORMAT_AZTEC
            )
            .enableAutoZoom()
            .build()
        val scanner = GmsBarcodeScanning.getClient(this)
//        val scanner = GmsBarcodeScanning.getClient(this, options)

        viewBinding.scanBtn.setOnClickListener{
            scanner.startScan()
                .addOnSuccessListener { barcode ->
                    // Task completed successfully
                    barcode.rawValue?.let {
                        Log.e(TAG, it)
                        viewBinding.scanText.text = it
                    }

                }
                .addOnCanceledListener {
                    // Task canceled
                    Toast.makeText(baseContext, "취소 하였습니다.", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    // Task failed with an exception
                    e.printStackTrace()
                }
        }
    }
}