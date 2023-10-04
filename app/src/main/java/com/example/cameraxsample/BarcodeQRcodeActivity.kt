package com.example.cameraxsample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cameraxsample.databinding.ActivityBarcodeQrcodeBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class BarcodeQRcodeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityBarcodeQrcodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityBarcodeQrcodeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
            ScanContract()
        ) { result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(this@BarcodeQRcodeActivity, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    this@BarcodeQRcodeActivity,
                    "Scanned: " + result.contents,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        barcodeLauncher.launch(ScanOptions())

    }
}