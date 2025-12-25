package com.example.fitpet.ui.statistics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Legend() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LegendItem(Color(0xFFD1FAE5), "Выполнено")
        LegendItem(Color(0xFF7C3AED), "Сегодня")
        LegendItem(Color.Transparent, "Выбрано", border = true)
    }
}