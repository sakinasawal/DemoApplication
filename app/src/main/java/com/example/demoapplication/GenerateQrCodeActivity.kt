package com.example.demoapplication

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapplication.MainActivity.Companion.hideSoftInput
import com.example.demoapplication.databinding.ActivityQrCodeBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

class GenerateQrCodeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityQrCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQrCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnGenerateCode.setOnClickListener {
                val data = etData.text.toString().trim()
                if (data.isEmpty()){
                    Toast.makeText(applicationContext, "Enter text", Toast.LENGTH_SHORT).show()
                } else {
                    try {
                        val bitmap = generateQRCode(data)
                        ivQrCode.setImageBitmap(bitmap)
                    } catch (e: WriterException){
                        e.printStackTrace()
                    }
                    hideSoftInput(this@GenerateQrCodeActivity)
                }
                etData.text.clear()
                etData.clearFocus()
            }
        }
    }

    private fun generateQRCode(data: String): Bitmap? {
        val qrCodeWriter = QRCodeWriter()
        try {
            val bitMatrix: BitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 300, 300)

            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            return bmp
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return null
    }
}