package com.example.fitpet.data.achievements

import androidx.compose.ui.graphics.vector.ImageVector

enum class AchievementStatus {
    NOT_DONE,
    DONE_NOT_CLAIMED,
    DONE_CLAIMED
}

data class Achievement(
    val id: Int,
    val title: String,
    val description: String,
    val rewardIcon: Int,
    val rewardName: String,
    val typeIcon: Int, // Drawable resource ID
    val progress: Float, // 0f..1f
    val status: AchievementStatus
)
