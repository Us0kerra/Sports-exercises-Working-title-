package com.example.fitpet.ui.settings.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TimePickerRow(
    time: String,
    onTimeChange: (String) -> Unit
) {
    Text(text = "Время уведомлений: $time")
}
