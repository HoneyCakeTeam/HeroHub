package com.example.herohub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.herohub.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        val navController = findNavController(R.id.fragment_host)
//        NavigationUI.setupWithNavController(bottomNavigationView , navController)
    }
}