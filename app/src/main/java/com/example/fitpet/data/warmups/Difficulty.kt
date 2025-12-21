package com.example.fitpet.data.warmups

import androidx.compose.ui.graphics.Color

enum class Difficulty(
    val title: String,
    val backgroundColor: Color,
    val textColor: Color
) {
    BEGINNER(
        "Новичок",
        Color(0xFFD1FAE5), // зелёный фон
        Color(0xFF065F46)
    ),
    MEDIUM(
        "Средний",
        Color(0xFFFEF3C7), // жёлтый
        Color(0xFF92400E)
    ),
    ADVANCED(
        "Продвинутый",
        Color(0xFFFEE2E2), // красный
        Color(0xFF991B1B)
    )
}
