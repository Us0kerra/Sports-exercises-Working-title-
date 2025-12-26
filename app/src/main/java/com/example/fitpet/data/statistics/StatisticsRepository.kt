package com.example.fitpet.data.statistics

import com.example.fitpet.ui.statistics.StatisticsState
import java.time.LocalDate
import java.time.YearMonth
import kotlin.random.Random

object StatisticsRepository {
    private val cache = mutableMapOf<YearMonth, StatisticsState>()

    fun getStatisticsForMonth(month: YearMonth): StatisticsState {
        return cache.getOrPut(month) {
            // Mock data generation
            val random = Random(month.hashCode())
            val completedDays = (1..month.lengthOfMonth()).shuffled(random).take(random.nextInt(5, 20)).toSet()
            StatisticsState(
                calories = random.nextInt(2000, 5000),
                minutes = random.nextInt(120, 300),
                workouts = random.nextInt(5, 15),
                completedDays = completedDays,
                streak = random.nextInt(0, 15),
                currentMonth = month
            )
        }
    }

    fun addWorkout(calories: Int, minutes: Int) {
        val today = LocalDate.now()
        val month = YearMonth.from(today)
        val currentState = getStatisticsForMonth(month)
        
        val updatedState = currentState.copy(
            calories = currentState.calories + calories,
            minutes = currentState.minutes + minutes,
            workouts = currentState.workouts + 1,
            completedDays = currentState.completedDays + today.dayOfMonth
        )
        
        cache[month] = updatedState
    }
}
