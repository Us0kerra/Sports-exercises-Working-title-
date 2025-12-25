package com.example.fitpet.ui.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class DayStat(
    val calories: Int,
    val minutes: Int,
    val workouts: Int,
    val completedDays: Set<Int>,
    val selectedDay: Int,
    val streak: Int
)

class StatisticsViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        DayStat(
            calories = 320,
            minutes = 45,
            workouts = 3,
            completedDays = setOf(1, 2, 5, 7, 8, 9),
            selectedDay = 10,
            streak = 3
        )
    )
    val state = _state.asStateFlow()

    fun onDaySelected(day: Int) {
        _state.update { it.copy(selectedDay = day) }
    }
}
