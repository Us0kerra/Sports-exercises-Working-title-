package com.example.fitpet.ui.warmups.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text


import com.example.fitpet.data.warmups.WarmupType


@Composable
fun CategorySlider(
    selectedType: WarmupType,
    onSelected: (WarmupType) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(WarmupType.values().toList()) { type ->
            val selected = type == selectedType

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        if (selected) Color(0xFF9810FA) else Color(0xFFF3F4F6)
                    )
                    .clickable { onSelected(type) }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = type.title,
                    color = if (selected) Color.White else Color.Gray   ,
                    fontSize = 14.sp
                )
            }
        }
    }
}
