package com.example.fitpet.ui.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarGrid(
    currentMonth: YearMonth,
    completedDays: Set<Int>,
    selectedDate: LocalDate,
    onDayClick: (LocalDate) -> Unit
) {
    val daysOfWeek = listOf("ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС")
    
    val firstDayOfMonth = currentMonth.atDay(1).dayOfWeek
    val emptyCellsCount = (firstDayOfMonth.value - DayOfWeek.MONDAY.value + 7) % 7
    val daysInMonth = currentMonth.lengthOfMonth()
    
    val calendarCells = mutableListOf<LocalDate?>()
    repeat(emptyCellsCount) { calendarCells.add(null) }
    (1..daysInMonth).forEach { day -> calendarCells.add(currentMonth.atDay(day)) }

    val weeks = calendarCells.chunked(7)

    Column(modifier = Modifier.fillMaxWidth()) {
        // Weekday Headers
        Row(modifier = Modifier.fillMaxWidth()) {
            daysOfWeek.forEach { dayName ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.weight(1f).padding(vertical = 8.dp)
                ) {
                    Text(text = dayName, color = Color.Gray, textAlign = TextAlign.Center, fontSize = 12.sp)
                }
            }
        }

        // Calendar days
        weeks.forEach { week ->
            Row(modifier = Modifier.fillMaxWidth()) {
                week.forEach { date ->
                    DayCell(date, selectedDate, completedDays, onDayClick, Modifier.weight(1f))
                }
                // Fill remaining space in the last week
                if (week.size < 7) {
                    for (i in 1..(7 - week.size)) {
                        Box(Modifier.weight(1f).size(40.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun DayCell(
    date: LocalDate?,
    selectedDate: LocalDate,
    completedDays: Set<Int>,
    onDayClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (date != null) {
            val day = date.dayOfMonth
            val isCompleted = completedDays.contains(day)
            val isSelected = date == selectedDate

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
                        color = if (isSelected) Color(0xFF7C3AED) else Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { onDayClick(date) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day.toString(),
                    color = when {
                        isSelected -> Color(0xFF7C3AED)
                        isCompleted -> Color.Black
                        else -> Color.Black
                    }
                )
            }
        } else {
            // Empty cell
            Box(Modifier.size(40.dp))
        }
    }
}
