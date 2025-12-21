package com.example.fitpet.data.warmups

data class Warmup(
    val id: String,
    val title: String,
    val type: WarmupType,
    val durationMinutes: Int,
    val exercisesCount: Int,
    val difficulty: Difficulty,
    val imageRes: Int
)
