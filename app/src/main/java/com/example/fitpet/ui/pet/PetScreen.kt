package com.example.fitpet.ui.pet

import android.widget.Space
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpet.data.Pets.PetType
import com.example.fitpet.ui.pet.components.PetCard
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetScreen(
    viewModel: PetViewModel = viewModel()
) {
    val petName by viewModel.petName.collectAsState()
    val selectedPet by viewModel.petType.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Добро пожаловать!",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF9810FA)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Выберите своего питомца и дайте ему имя",
            fontSize = 13.sp,
            color = Color(0xFF6B7280)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Выберите питомца",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PetCard(
                title = "Синий питомец",
                color = Color(0xFF3B82F6),
                isSelected = selectedPet == PetType.BLUE,
                onClick = { viewModel.onPetSelected(PetType.BLUE) }
            )

            PetCard(
                title = "Зеленый питомец",
                color = Color(0xFF22C55E),
                isSelected = selectedPet == PetType.GREEN,
                onClick = { viewModel.onPetSelected(PetType.GREEN) }
            )

            PetCard(
                title = "Оранжевый питомец",
                color = Color(0xFFF97316),
                isSelected = selectedPet == PetType.ORANGE,
                onClick = { viewModel.onPetSelected(PetType.ORANGE) }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Дайте имя питомцу",
            fontSize = 12.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = petName,
            onValueChange = viewModel::onNameChange,
            placeholder = { Text("Имя") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF9FAFB),
                unfocusedContainerColor = Color(0xFFF9FAFB),
                disabledContainerColor = Color(0xFFF9FAFB),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = viewModel::createPet,
            enabled = petName.isNotBlank() && selectedPet != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9810FA)
            )
        ) {
            Text(
                text = "Я родился!",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

