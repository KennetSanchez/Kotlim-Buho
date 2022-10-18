package com.example.buho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.buho.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val binding: ActivityRegisterBinding by lazy{
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_register)

        binding.backButton.setOnClickListener{
            super.finishActivity(0)
        }

        binding.RARegisterBtn.setOnClickListener{
            super.finishActivity(1)
            Toast.makeText(applicationContext, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
            super.finish()
            binding.RARegisterBtn.setBackgroundColor(R.color.btn_danger)
        }


    }
}
