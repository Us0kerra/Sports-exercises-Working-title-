package com.example.fitpet.ui.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height

@Composable
fun CalendarGrid(
    completedDays: Set<Int>,
    selectedDay: Int,
    onDayClick: (Int) -> Unit
) {
    val days = (1..31).toList()

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        userScrollEnabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp) // фиксируем высоту
    ) {
        items(days) { day ->
            val isCompleted = completedDays.contains(day)
            val isSelected = day == selectedDay

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
                    .background(
                        color = when {
                            isSelected -> Color(0xFF7C3AED).copy(alpha = 0.2f)
                            isCompleted -> Color(0xFFD1FAE5)
                            else -> Color.Transparent
                        },
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = if (isSelected) 2.dp else 0.dp,
                        color = Color(0xFF7C3AED),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { onDayClick(day) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day.toString(),
                    color = when {
                        isSelected -> Color(0xFF7C3AED)
                        isCompleted -> Color.Black
                        else -> Color.LightGray
                    }
                )
            }
        }
    }
}
