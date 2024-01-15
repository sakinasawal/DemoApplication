package com.example.demoapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapplication.databinding.ActivityHomeBinding
import com.example.demoapplication.databinding.DialogWelcomeBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    @SuppressLint("SetTextI18n")
    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result: ScanIntentResult ->
        if (result.contents != null) {
            val dialogBinding = DialogWelcomeBinding.inflate(LayoutInflater.from(this@HomeActivity))
            val builder = AlertDialog.Builder(this@HomeActivity)
                .setView(dialogBinding.root)
                .setCancelable(true)

            val dialog = builder.create()
            dialog.show()

            dialogBinding.apply {
                tvDialogMessage.text = "This is ${result.contents} qr code"
                btnDismiss.setOnClickListener{
                    dialog.dismiss()
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnGenerateCode.setOnClickListener {
                val intent = Intent(this@HomeActivity, GenerateQrCodeActivity::class.java)
                startActivity(intent)
            }

            btnScanCode.setOnClickListener {
                val scanOptions = ScanOptions()
                scanOptions.setBeepEnabled(true)
                scanOptions.setPrompt("")
                scanOptions.setOrientationLocked(true)
                scanOptions.setBarcodeImageEnabled(true)
                scanOptions.captureActivity = ScanQrCodeActivity::class.java
                barcodeLauncher.launch(scanOptions)
            }
        }

    }
}