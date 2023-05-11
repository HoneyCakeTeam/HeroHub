package com.example.herohub.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.herohub.R
import com.example.herohub.databinding.ActivityMainBinding
import com.example.herohub.utills.SharedPreferencesUtils

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

        setupActionBarWithNavController(navController)
        val topLevelDestinations = setOf(
            R.id.home_fragment,
            R.id.search_fragment,
            R.id.favourite_fragment
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in topLevelDestinations) {
                binding.bottomNav.visibility = View.VISIBLE
                supportActionBar?.hide()
            } else {
                binding.bottomNav.visibility = View.GONE
                supportActionBar?.show()
            }
        }
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_host)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}