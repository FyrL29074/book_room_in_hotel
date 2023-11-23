package com.fyrl29074.book_room_in_hotel.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.fyrl29074.book_room_in_hotel.databinding.ActivityMainBinding
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            findNavController(androidx.navigation.fragment.R.id.nav_host_fragment_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun showToolbar(title: String) {
        binding.toolbar.isVisible = true
        binding.title.text = title
    }

    fun hideToolbar() {
        binding.toolbar.title = ""
        binding.toolbar.isVisible = false
    }
}