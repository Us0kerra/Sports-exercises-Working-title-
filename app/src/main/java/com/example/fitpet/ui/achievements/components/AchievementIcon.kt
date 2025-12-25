package com.example.fitpet.ui.achievements.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fitpet.data.achievements.AchievementStatus

@Composable
fun AchievementIcon(
    icon: Int,
    status: AchievementStatus
) {
    val backgroundColor = when (status) {
        AchievementStatus.NOT_DONE -> Color(0xFF9810FA)      // фиолетовый
        AchievementStatus.DONE_NOT_CLAIMED -> Color(0xFFFF9800) // оранжевый
        AchievementStatus.DONE_CLAIMED -> Color(0xFF4CAF50)          // зелёный
    }

    Box(
        modifier = Modifier
            .size(48.dp)
            .background(backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.White
        )
    }
}
