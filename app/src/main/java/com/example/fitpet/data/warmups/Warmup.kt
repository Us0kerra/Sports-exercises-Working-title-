package com.example.fitpet.data.warmups

data class Warmup(
    val id: String,
    val name: String,
    val description: String,
    val type: WarmupType,
    val exercises: List<Exercise>
)
