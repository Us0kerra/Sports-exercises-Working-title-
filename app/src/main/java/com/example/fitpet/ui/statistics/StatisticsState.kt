package com.example.fitpet.ui.statistics

import java.time.LocalDate
import java.time.YearMonth

data class StatisticsState(
    val calories: Int = 0,
    val minutes: Int = 0,
    val workouts: Int = 0,
    val completedDays: Set<Int> = emptySet(),
    val selectedDate: LocalDate = LocalDate.now(),
    val currentMonth: YearMonth = YearMonth.now(),
    val streak: Int = 0,
) {
    val title: String
        get() = if (selectedDate == LocalDate.now()) {
            "Статистика сегодня"
        } else {
            "Статистика ${selectedDate.dayOfMonth} ${selectedDate.month.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale("ru"))} ${selectedDate.year}"
        }
}
