package com.example.herohub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.herohub.R
import com.example.herohub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_host) as NavHostFragment
//        navController = navHostFragment.navController
//        NavigationUI.setupWithNavController(binding.bottomNav, navController)
//        NavigationUI.setupActionBarWithNavController(this , navController )
    }

//    override fun onSupportNavigateUp(): Boolean {
//        findNavController(R.id.fragment_host).navigateUp()
//        return true
//    }


    override fun onResume() {
        super.onResume()
        navController = findNavController(R.id.fragment_host)
        binding.bottomNav.setupWithNavController(navController)
//        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}