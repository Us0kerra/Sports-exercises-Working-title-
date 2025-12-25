package com.example.fitpet.ui.achievements.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpet.data.achievements.Achievement
import com.example.fitpet.data.achievements.AchievementStatus

@Composable
fun TitleRow(achievement: Achievement, onClaimReward: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(1f)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = achievement.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                if (achievement.status != AchievementStatus.NOT_DONE) {
                    Spacer(Modifier.width(8.dp))
                }


                if (achievement.status == AchievementStatus.DONE_NOT_CLAIMED) {
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = onClaimReward,
                        modifier = Modifier.align(Alignment.Top)
                    ) {
                        Text("Забрать")
                    }

                }
                if (
                    achievement.status == AchievementStatus.DONE_NOT_CLAIMED
                ) {
                    Spacer(Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.Black.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "ГОТОВО!",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        if (achievement.status == AchievementStatus.DONE_CLAIMED) {
            CheckMark()
        }
    }
}
