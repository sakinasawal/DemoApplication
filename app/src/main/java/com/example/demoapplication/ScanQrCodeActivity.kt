package com.example.demoapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapplication.databinding.ActivityScanQrCodeBinding
import com.journeyapps.barcodescanner.CaptureManager


class ScanQrCodeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityScanQrCodeBinding
    private var capture: CaptureManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScanQrCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        capture = CaptureManager(this, binding.barcodeView)
        capture!!.initializeFromIntent(intent, savedInstanceState)
        capture!!.decode()

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        capture!!.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        capture!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture!!.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return binding.barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }

}