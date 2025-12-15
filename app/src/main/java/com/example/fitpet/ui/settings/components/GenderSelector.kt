package com.example.fitpet.ui.settings.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitpet.data.model.Gender

@Composable
fun GenderSelector(
    selected: Gender,
    onSelect: (Gender) -> Unit
) {
    Row {
        Button(
            onClick = { onSelect(Gender.MALE) },
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text("Мужской")
        }

        Button(
            onClick = { onSelect(Gender.FEMALE) }
        ) {
            Text("Женский")
        }
    }
}
