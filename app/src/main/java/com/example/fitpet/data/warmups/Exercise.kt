package com.example.fitpet.data.warmups

data class Exercise(
    val id: String,
    val name: String,
    val description: String,
    val durationSeconds: Int = 0,
    val repetitions: Int = 0,
    val imageRes: Int,
    val restSeconds: Int = 0
)
