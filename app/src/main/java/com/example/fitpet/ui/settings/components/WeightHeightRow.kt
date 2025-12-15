package com.example.fitpet.ui.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeightHeightRow(
    label: String,
    value: Int,
    min: Int,
    max: Int,
    unit: String,
    onChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = { if (value > min) onChange(value - 1) }
            ) {
                Text("-")
            }

            Spacer(Modifier.width(8.dp))

            Text(text = "$value $unit")

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = { if (value < max) onChange(value + 1) }
            ) {
                Text("+")
            }
        }
    }
}
