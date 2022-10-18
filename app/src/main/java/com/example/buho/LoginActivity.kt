package com.example.buho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.buho.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding:ActivityLoginBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener{
            val username = binding.usernameET.text.toString()
            val pass = binding.passET.text.toString()

            if(username==R.string.LA_dummy_un.toString()&&pass==R.string.LA_dummy_pass.toString()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}