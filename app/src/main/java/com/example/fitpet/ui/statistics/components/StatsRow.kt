package com.example.fitpet.ui.statistics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.fitpet.ui.statistics.StatisticsState

@Composable
fun StatsRow(state: StatisticsState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        StatCard("🔥", state.calories, "ккал", Color(0xFFFDF2F8))
        StatCard("⏱", state.minutes, "мин.", Color(0xFFFFF7ED))
        StatCard("🏋️", state.workouts, "Тренировки", Color(0xFFF0F9FF))
    }
}
