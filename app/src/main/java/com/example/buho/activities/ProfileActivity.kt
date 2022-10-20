package com.example.buho.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.buho.databinding.ProfilePageBinding

class ProfileActivity : AppCompatActivity() {
    private val binding:ProfilePageBinding by lazy{
        ProfilePageBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)
    }
}