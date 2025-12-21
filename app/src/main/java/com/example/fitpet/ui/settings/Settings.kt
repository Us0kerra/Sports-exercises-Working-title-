package com.example.fitpet.ui.settings

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalTime

@Parcelize
data class Settings(
    val notifications: Boolean = true,
    val notificationTime: LocalTime = LocalTime.of(9, 0),
    val sound: Boolean = true,
    val weight: Int = 70,
    val height: Int = 170,
    val gender: Gender = Gender.MALE
) : Parcelable

enum class Gender {
    MALE, FEMALE
}