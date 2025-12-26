package com.example.fitpet.ui.warmups

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpet.data.warmups.Exercise
import com.example.fitpet.data.warmups.Warmup

@Composable
fun WarmupDetailsScreen(warmup: Warmup) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Handle back */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(text = warmup.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            IconButton(onClick = { /* Handle close */ }) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        }

        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = warmup.imageRes),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${warmup.durationMinutes} мин • ${warmup.durationMinutes * 10} ккал", fontSize = 16.sp, color = Color.Gray)
        }

        // Exercises List
        Text(
            text = "Упражнения (${warmup.exercises.size})",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            itemsIndexed(warmup.exercises) { index, exercise ->
                ExerciseRow(exercise = exercise, number = index + 1)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ExerciseRow(exercise: Exercise, number: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E0E0)),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = exercise.imageRes), contentDescription = null, modifier = Modifier.size(32.dp))
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = exercise.name, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            val details = if (exercise.repetitions > 0) {
                "${exercise.repetitions} повт."
            } else {
                "${exercise.durationSeconds} сек"
            }
            Text(text = details, fontSize = 14.sp, color = Color.Gray)
        }
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = number.toString(), color = Color.Black)
        }
    }
}
