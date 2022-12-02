package com.example.buho.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.buho.fragments.ActivitiesFragment
import com.example.buho.fragments.AssistanceFragment
import com.example.buho.fragments.HomeFragment
import com.example.buho.R
import com.example.buho.databinding.ActivityMainBinding
import com.example.buho.models.Assistance
import com.example.buho.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {


    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var user: User


    private fun loadUser():User?{
        val sp = getSharedPreferences("Buho", MODE_PRIVATE)
        val json = sp.getString("user", "NO_USER")
        return if(json == "NO_USER"){
            null
        }else{
            Gson().fromJson(json, User::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val homeFragment = HomeFragment(binding.root)
        val activitiesFragment = ActivitiesFragment(binding.root)
        val assistanceFragment = AssistanceFragment()
        val assistance=intent.extras?.getSerializable("assistance")

        val f: Fragment = AssistanceFragment()
        val args = Bundle() //* Bundle a recibir con datos.

        args.putSerializable(
            "Assistance",
            assistance
        )

        f.arguments = args

        supportFragmentManager
            .beginTransaction()
            .commit()

        setCurrentFragment(homeFragment)

        val user = loadUser()
        if (user == null || Firebase.auth.currentUser == null || Firebase.auth.currentUser?.isEmailVerified == false) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        } else {
            this.user = user
            Toast.makeText(this, "Hola ${user.name}", Toast.LENGTH_LONG).show()

            binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.homeItem -> setCurrentFragment(homeFragment)
                    R.id.activitiesItem -> setCurrentFragment(activitiesFragment)

                    R.id.assistenceItem -> setCurrentFragment(assistanceFragment)
                    else -> {
                        false
                    }
                }
            }
        }

        binding.logoutIV.setOnClickListener {
            finish()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            val sp = getSharedPreferences("Buho", MODE_PRIVATE)
            sp.edit().clear().apply()
            Firebase.auth.signOut()
        }
    }


    private fun setCurrentFragment(fragment: Fragment) : Boolean {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }

        return true
    }
}