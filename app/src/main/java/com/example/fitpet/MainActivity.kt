package com.example.fitpet

import android.os.Build
import android.os.Bundle
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.fitpet.data.repository.SettingsRepository
import com.example.fitpet.notifications.NotificationScheduler
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.lifecycle.lifecycleScope
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.launch
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitpet.data.PetViewModelFactory
import com.example.fitpet.data.warmups.WarmupRepository
import com.example.fitpet.ui.Screen
import com.example.fitpet.ui.achievements.AchievementsScreen
import com.example.fitpet.ui.achievements.AchievementsViewModel
import com.example.fitpet.ui.bottomNavItems
import com.example.fitpet.ui.pet.PetScreen
import com.example.fitpet.ui.pet.PetViewModel
import com.example.fitpet.ui.settings.SettingsScreen
import com.example.fitpet.ui.settings.SettingsViewModel
import com.example.fitpet.ui.settings.SettingsViewModelFactory
import com.example.fitpet.ui.statistics.StatisticsScreen
import com.example.fitpet.ui.statistics.StatisticsViewModel
import com.example.fitpet.ui.warmups.WarmupDetailsScreen
import com.example.fitpet.ui.warmups.WarmupsScreen
import com.example.fitpet.ui.warmups.WarmupsViewModel

class MainActivity : AppCompatActivity() {

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

        setContent {
            FitPetApp()
        }
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

@Composable
fun FitPetApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.title, fontSize = 10.sp) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController, 
            startDestination = Screen.Warmups.route, 
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Warmups.route) {
                val viewModel: WarmupsViewModel = viewModel()
                WarmupsScreen(viewModel = viewModel, navController = navController)
            }
            composable(
                route = Screen.WarmupDetails.routeWithArgs,
                arguments = listOf(navArgument(Screen.WarmupDetails.warmupIdArg) { type = NavType.StringType })
            ) { backStackEntry ->
                val warmupId = backStackEntry.arguments?.getString(Screen.WarmupDetails.warmupIdArg)
                val warmup = WarmupRepository.getWarmupById(warmupId)
                if (warmup != null) {
                    WarmupDetailsScreen(warmup = warmup)
                }
            }
            composable(Screen.Achievements.route) {
                val viewModel: AchievementsViewModel = viewModel()
                AchievementsScreen(viewModel)
            }
            composable(Screen.Pet.route) {
                val context = LocalContext.current
                val repository = (context.applicationContext as FitPetApplication).petRepository
                val factory = PetViewModelFactory(repository)
                val viewModel: PetViewModel = viewModel(factory = factory)
                PetScreen(
                    viewModel = viewModel,
                    onNavigateToWarmups = { navController.navigate(Screen.Warmups.route) }
                )
            }
            composable(Screen.Statistics.route) {
                val viewModel: StatisticsViewModel = viewModel()
                StatisticsScreen(viewModel)
            }
            composable(Screen.Settings.route) {
                val context = LocalContext.current
                val repository = SettingsRepository(context)
                val factory = SettingsViewModelFactory(repository)
                val viewModel: SettingsViewModel = viewModel(factory = factory)
                SettingsScreen(viewModel)
            }
        }
    }
}
