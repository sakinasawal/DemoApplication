package com.example.demoapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapplication.databinding.ActivityMainBinding
import com.example.demoapplication.databinding.DialogWelcomeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object{
        fun hideSoftInput(activity : AppCompatActivity){
            activity.currentFocus?.let {
                val manager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvGreetings.text = "Welcome to Clarity Techworks"
            btnEnter.setOnClickListener {
                val userName = etName.text.toString().trim()

                val dialogBinding = DialogWelcomeBinding.inflate(LayoutInflater.from(this@MainActivity))

                val builder = AlertDialog.Builder(this@MainActivity)
                    .setView(dialogBinding.root)
                    .setCancelable(true)

                val dialog = builder.create()
                dialog.show()

                dialogBinding.apply{
                    if (userName.isEmpty()){
                        tvDialogMessage.text = "Please Enter Your Name!"
                        btnDismiss.text = "Try Again"
                        btnDismiss.setOnClickListener{
                            dialog.dismiss()
                        }
                    } else if (userName[0].isLowerCase()){
                        tvDialogMessage.text = "Please Start Your Name with Capital Letter!"
                        btnDismiss.text = "Try Again"
                        btnDismiss.setOnClickListener{
                            dialog.dismiss()
                        }
                    } else {
                        tvDialogMessage.text = "Hello ${userName}, nice to meet you"
                        btnDismiss.setOnClickListener{
                            dialog.dismiss()
                        }
                        tvGreetings.text = "Hello ${userName}, Welcome to Clarity Techworks"
                    }
                    etName.text.clear()
                    etName.clearFocus()
                    hideSoftInput(this@MainActivity)
                }

            }

            btnEnterQrCode.setOnClickListener{
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
            }

        }
    }
}