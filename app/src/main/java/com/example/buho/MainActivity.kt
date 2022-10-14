package com.example.buho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.buho.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    //private lateinit var homeFragment:HomeFragment
    //private lateinit var activitiesFragment:ActivitiesFragment
    private lateinit var assistanceFragment:AssistanceFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //homeFragment=HomeFragment.newInstance()
        //activitiesFragment=ActivitiesFragment.newInstance()
        assistanceFragment=AssistanceFragment.newInstance()
        //showFragment(homeFragment)
        binding.bottomNavigationView.setOnItemReselectedListener{menuItem->
            if(menuItem.itemId==R.id.homeItem){
                //showFragment(homeFragment)
            } else if(menuItem.itemId==R.id.activitiesItem){
                //showFragment(activitiesFragment)
            } else if(menuItem.itemId==R.id.assistenceItem){
                showFragment(assistanceFragment)
            }
            true
        }

    }

    private fun showFragment(fragment: Fragment) {
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}