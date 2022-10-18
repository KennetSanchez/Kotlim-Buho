package com.example.buho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.buho.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val activitiesFragment = ActivitiesFragment()
        val assistanceFragment = AssistanceFragment()


        setCurrentFragment(homeFragment)


        binding.bottomNavigationView.setOnItemSelectedListener{menuItem->
            when(menuItem.itemId){
                R.id.homeItem -> setCurrentFragment(homeFragment)
                R.id.activitiesItem -> setCurrentFragment(activitiesFragment)
                R.id.assistenceItem -> setCurrentFragment(assistanceFragment)
                else -> {false}
            }
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