package com.example.buho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.buho.databinding.ActivityMainBinding

class RegisterActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_register)
    }
}
