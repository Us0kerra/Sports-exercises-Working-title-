package com.example.fitpet.ui.statistics.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment


@Composable
fun Calendar(
    completedDays: Set<Int>,
    selectedDay: Int,
    streak: Int,
    onDayClick: (Int) -> Unit
) {

    Text(
        text = "Календарь прогресса",
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )

    Text(
        text = "Отслеживайте свой путь тренировок",
        fontSize = 13.sp,
        color = Color.Gray
    )

    Spacer(Modifier.height(12.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
//        InfoChip("Этот месяц", "6 дней", Color(0xFFF3E8FF))
//        InfoChip("Текущая серия", "$streak дня", Color(0xFFFCE7F3))
    }

    Spacer(Modifier.height(16.dp))

//    Text(
//        text = "Декабрь 2025",
//        modifier = Modifier.align(Alignment.Start),
//        fontWeight = FontWeight.Medium
//    )

    Spacer(Modifier.height(12.dp))

    CalendarGrid(
        completedDays = completedDays,
        selectedDay = selectedDay,
        onDayClick = onDayClick
    )

    Spacer(Modifier.height(16.dp))

    Legend()
}
