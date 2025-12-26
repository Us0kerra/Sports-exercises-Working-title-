package com.example.fitpet.data.warmups

import androidx.compose.ui.graphics.Color

enum class Difficulty(val title: String, val textColor: Color, val backgroundColor: Color) {
    BEGINNER("Новичок", Color(0xFF16A34A), Color(0xFFDCFCE7)),
    MEDIUM("Опытный", Color(0xFFEAB308), Color(0xFFFEFCE8)),
    ADVANCED("Эксперт", Color(0xFFDC2626), Color(0xFFFEE2E2))
}
