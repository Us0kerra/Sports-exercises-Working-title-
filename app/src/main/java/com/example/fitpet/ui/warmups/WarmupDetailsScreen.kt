package com.example.fitpet.ui.warmups

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitpet.data.warmups.Warmup

@Composable
fun WarmupDetailsScreen(
    warmup: Warmup,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Default.Close, contentDescription = "Close")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = warmup.name)
        Text(text = warmup.description)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(warmup.exercises) { exercise ->
                Row {
                    Image(
                        painter = painterResource(id = exercise.imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )
                    Column {
                        Text(text = exercise.name)
                        Text(text = exercise.description)
                        Text(text = "${exercise.durationSeconds}s, ${exercise.repetitions} reps")
                    }
                }
            }
        }
    }
}