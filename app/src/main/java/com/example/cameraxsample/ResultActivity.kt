package com.example.cameraxsample

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cameraxsample.MainActivity.Companion.SEND_URI
import com.example.cameraxsample.databinding.ActivityResultBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer

class ResultActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityResultBinding

    private var recognizer: TextRecognizer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val sendURI = intent.getStringExtra(SEND_URI)

        viewBinding.imageView.setImageURI(Uri.parse(sendURI))
        viewBinding.textView.text = sendURI;
        recognizer = TextRecognition.getClient()

    }

}