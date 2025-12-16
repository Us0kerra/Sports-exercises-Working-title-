package com.example.fitpet.ui.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.fitpet.data.model.Gender
@Composable
fun GenderSelector(selected: Gender, onSelect: (Gender) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Мужской
        Button(
            onClick = { onSelect(Gender.MALE) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selected == Gender.MALE) Color(0xFF7C4DFF) else Color(0xFFE0E0E0),
                contentColor = if (selected == Gender.MALE) Color.White else Color.Black
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text("Мужской")
        }

        Spacer(Modifier.width(16.dp))

        // Женский
        Button(
            onClick = { onSelect(Gender.FEMALE) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selected == Gender.FEMALE) Color(0xFF7C4DFF) else Color(0xFFE0E0E0),
                contentColor = if (selected == Gender.FEMALE) Color.White else Color.Black
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text("Женский")
        }
    }
}


