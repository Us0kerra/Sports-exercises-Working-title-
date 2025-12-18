package com.example.fitpet

import android.os.Build
import android.os.Bundle
import android.content.pm.PackageManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fitpet.databinding.ActivityMainBinding
import com.example.fitpet.data.repository.SettingsRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.lifecycle.lifecycleScope
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermissionIfNeeded()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Подписываемся на изменения настроек, чтобы переключать светлую/тёмную тему
        val settingsRepository = SettingsRepository(applicationContext)
        settingsRepository.settingsFlow
            .onEach { settings ->
                val mode = if (settings.darkMode) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
                if (AppCompatDelegate.getDefaultNightMode() != mode) {
                    AppCompatDelegate.setDefaultNightMode(mode)
                }
            }
            .launchIn(lifecycleScope)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_pet, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_achievements, R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = android.Manifest.permission.POST_NOTIFICATIONS
            if (ContextCompat.checkSelfPermission(this, permission)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), 1001)
            }
        }
    }
}