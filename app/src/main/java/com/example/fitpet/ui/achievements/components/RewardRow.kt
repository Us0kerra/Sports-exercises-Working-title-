package com.example.fitpet.ui.achievements.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpet.data.achievements.Achievement

@Composable
fun RewardRow(achievement: Achievement) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(achievement.rewardIcon),
            contentDescription = null,
            tint = Color(0xFFFFC107),
            modifier = Modifier.size(18.dp)
        )

        Spacer(Modifier.width(6.dp))

        Text(
            text = "Награда:",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(Modifier.width(4.dp))

        Text(
            text = achievement.rewardName,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
