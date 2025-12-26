package com.example.fitpet.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Pet : Screen("pet", "Питомец", Icons.Default.Home)
    object Statistics : Screen("statistics", "Статистика", Icons.Default.QueryStats)
    object Warmups : Screen("warmups", "Разминки", Icons.Default.FitnessCenter)
    object Achievements : Screen("achievements", "Достижения", Icons.Default.EmojiEvents)
    object Settings : Screen("settings", "Настройки", Icons.Default.Settings)

    object WarmupDetails : Screen("warmup_details", "Warmup Details", Icons.Default.Home) { // icon doesn't matter
        const val routeWithArgs = "warmup_details/{warmupId}"
        const val warmupIdArg = "warmupId"
        fun createRoute(warmupId: String) = "warmup_details/$warmupId"
    }
}

val bottomNavItems = listOf(
    Screen.Pet,
    Screen.Statistics,
    Screen.Warmups,
    Screen.Achievements,
    Screen.Settings
)
