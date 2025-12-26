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
import com.example.fitpet.notifications.NotificationScheduler
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.lifecycle.lifecycleScope
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermissionIfNeeded()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Подписываемся на изменения настроек
        val settingsRepository = SettingsRepository(applicationContext)
        
        // Создаем канал уведомлений при запуске
        NotificationScheduler.createNotificationChannel(applicationContext)
        
        settingsRepository.settingsFlow
            .onEach { settings ->
                // Переключаем светлую/тёмную тему
                val mode = if (settings.darkMode) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
                if (AppCompatDelegate.getDefaultNightMode() != mode) {
                    AppCompatDelegate.setDefaultNightMode(mode)
                }
                
                // Инициализируем уведомления при запуске приложения
                if (settings.notifications && settings.notificationTimes.isNotEmpty()) {
                    NotificationScheduler.scheduleDailyReminders(
                        applicationContext,
                        settings.notificationTimes
                    )
                }
            }
            .launchIn(lifecycleScope)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_pet, R.id.navigation_statistics, R.id.navigation_warmups, R.id.navigation_achievements, R.id.navigation_settings
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