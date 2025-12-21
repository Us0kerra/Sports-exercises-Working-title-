package com.example.fitpet.ui.warmups

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpet.data.warmups.Warmup
import com.example.fitpet.data.warmups.WarmupType
import com.example.fitpet.ui.warmups.components.WarmupCard
import com.example.fitpet.ui.warmups.components.CategorySlider

@Composable
fun WarmupsScreen(viewModel: WarmupsViewModel
) {
    val selectedType by viewModel.selectedType.collectAsState()
    val warmups:List<Warmup> by viewModel.warmups.collectAsState()
    val filteredWarmups = if (selectedType == WarmupType.ALL) {
        warmups
    } else {
        warmups.filter { it.type == selectedType }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Доступные разминки",
            fontSize = 18.sp,
            color = Color(0xFF9810FA)
        )

        Text(
            text = "Выберите следующую разминку",
            fontSize = 14.sp,
            color = Color(0xFF6B7280)
        )

        Spacer(Modifier.height(12.dp))

        CategorySlider(
            selectedType = selectedType,
            onSelected = viewModel::selectType
        )

        Spacer(Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(filteredWarmups) { warmup ->
                WarmupCard(
                    warmup = warmup,
                    onPlayClick = {
                        // TODO: запуск разминки
                    }
                )
            }
        }
    }
}