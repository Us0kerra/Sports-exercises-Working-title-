package com.example.fitpet.ui.warmups.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpet.data.warmups.Warmup
import java.util.concurrent.TimeUnit


@Composable
fun WarmupCard(
    warmup: Warmup,
    onPlayClick: () -> Unit
) {
    val durationInMinutes = TimeUnit.SECONDS.toMinutes(
        warmup.exercises.sumOf { it.durationSeconds + it.restSeconds }.toLong()
    )
    if (warmup.exercises.isNotEmpty()) { // safety check
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(warmup.exercises.first().imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(warmup.name, fontWeight = FontWeight.Bold)
                    Text(
                        "${warmup.type.title} • $durationInMinutes мин • ${warmup.exercises.size} упражнений",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                IconButton(
                    onClick = onPlayClick,
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF9810FA), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}