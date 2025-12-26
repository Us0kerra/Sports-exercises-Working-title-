package com.example.fitpet.data.warmups

data class Exercise(
    val id: String,
    val name: String,
    val description: String,
    val durationSeconds: Int,
    val repetitions: Int,
    val imageRes: Int,
    val restSeconds: Int,
    val caloriesPerMinute: Int = 0
)
