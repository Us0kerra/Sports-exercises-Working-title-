package com.example.fitpet.ui.settings.components

import android.app.TimePickerDialog
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun TimePickerRow(
    time: String,
    onTimeChange: (String) -> Unit,
    onRemove: (() -> Unit)? = null
) {
    var showDialog by remember { mutableStateOf(false) }
    val isTimeValid = time.contains(":") && time != "Выберите время"
    val hour = if (isTimeValid) {
        time.substringBefore(":").toIntOrNull() ?: 9
    } else {
        9
    }
    val minute = if (isTimeValid) {
        time.substringAfter(":").toIntOrNull() ?: 0
    } else {
        0
    }
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable { showDialog = true }
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = time,
                color = if (isTimeValid) Color.Unspecified else Color.Gray
            )
        }
        
        if (onRemove != null && isTimeValid) {
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Удалить",
                    tint = Color.Gray
                )
            }
        }
    }

    if (showDialog) {
        TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                val formatted = String.format("%02d:%02d", selectedHour, selectedMinute)
                onTimeChange(formatted)
                showDialog = false
            },
            hour,
            minute,
            true
        ).show()
    }
}
