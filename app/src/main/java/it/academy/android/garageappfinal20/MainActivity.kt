package it.academy.android.garageappfinal20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController // per la navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inizializza il Navigation Controller e associa il grafo di navigazione
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.home_activity_navFragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}