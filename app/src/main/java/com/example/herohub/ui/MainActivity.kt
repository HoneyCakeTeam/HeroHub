package com.example.herohub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.herohub.R
import com.example.herohub.databinding.ActivityMainBinding
import com.example.herohub.utills.shared.SharedPreferencesUtils

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        SharedPreferencesUtils.initShared(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        setupNavigation()
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.fragment_host)
        // NavigationUI.setupActionBarWithNavController(this, navController)
        binding.bottomNav.setupWithNavController(navController)
    }
    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/
}