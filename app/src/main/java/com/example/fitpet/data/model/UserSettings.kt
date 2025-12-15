package com.example.fitpet.data.model

data class UserSettings(
    val notifications: Boolean = true,
    val sound: Boolean = true,
    val darkMode: Boolean = false,
    val weight: Int = 70,
    val height: Int = 175,
    val gender: Gender = Gender.MALE,
    val notificationTime: String = "09:00"
)
