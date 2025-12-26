package com.example.fitpet.data

data class Pet(
    val name: String,
    val level: Int = 1,
    val experience: Int = 0,
    val health: Int = 100,
    val petType: PetType
)

enum class PetType {
    BLUE, GREEN, ORANGE
}