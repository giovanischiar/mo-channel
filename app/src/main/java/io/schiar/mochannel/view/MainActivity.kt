package io.schiar.mochannel.view

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import io.schiar.mochannel.R
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel
import io.schiar.mochannel.viewmodel.util.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    lateinit var tvShowsViewModel: TVShowsViewModel
    lateinit var tvShowViewModel: TVShowViewModel
    lateinit var videoViewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        val viewModelFactory = ViewModelFactory(repository = MainRepository())
        val viewModelProvider = ViewModelProvider(owner = this, factory = viewModelFactory)

        tvShowsViewModel = viewModelProvider[TVShowsViewModel::class.java]
        tvShowViewModel = viewModelProvider[TVShowViewModel::class.java]
        videoViewModel = viewModelProvider[VideoViewModel::class.java]

        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navHostFragment = fragment as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(activity = this, navController = navController)

        onBackPressedDispatcher.addCallback(
            owner = this, onBackPressedCallback = BackPressedHandler()
        )
    }

    inner class BackPressedHandler : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            navController.navigateUp()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}