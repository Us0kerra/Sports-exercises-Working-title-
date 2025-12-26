package com.example.fitpet.ui.pet

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitpet.R
import com.example.fitpet.data.PetType
import com.example.fitpet.ui.pet.components.PetCard

@Composable
fun PetScreen(
    viewModel: PetViewModel = viewModel(),
    onNavigateToWarmups: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (uiState.isPetCreated) {
        PetDetailScreen(uiState, viewModel, onNavigateToWarmups)
    } else {
        CreatePetScreen(uiState, viewModel::onNameChange, viewModel::onPetSelected, viewModel::createPet)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(
    uiState: PetScreenState,
    viewModel: PetViewModel,
    onNavigateToWarmups: () -> Unit
) {
    val pet = uiState.pet!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uiState.isEditingName) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = uiState.name,
                    onValueChange = viewModel::onNameChange,
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = viewModel::savePetName) {
                    Text("Сохранить")
                }
            }
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pet.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = viewModel::onEditName) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit pet name")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Опыт")
            LinearProgressIndicator(
                progress = pet.experience / 100f,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Здоровье")
            LinearProgressIndicator(
                progress = pet.health / 100f,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cat_base),
                    contentDescription = "Pet image",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column {
                Button(
                    onClick = { /* TODO */ },
                    shape = CircleShape,
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(Icons.Default.Checkroom, contentDescription = "Clothing")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* TODO */ },
                    shape = CircleShape,
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(Icons.Default.Fastfood, contentDescription = "Food")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNavigateToWarmups,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9810FA)
            )
        ) {
            Text(
                text = "Начать разминку",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePetScreen(
    uiState: PetScreenState,
    onNameChange: (String) -> Unit,
    onPetSelected: (PetType) -> Unit,
    onCreatePet: () -> Unit
) {
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
            text = "Выберите питомца и дайте ему имя",
            fontSize = 13.sp,
            color = Color(0xFF6B7280)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Выберите",
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
                title = "Кошка",
                color = Color(0xFF3B82F6),
                isSelected = uiState.petType == PetType.BLUE,
                onClick = { onPetSelected(PetType.BLUE) }
            )

            PetCard(
                title = "Собачка",
                color = Color(0xFF22C55E),
                isSelected = uiState.petType == PetType.GREEN,
                onClick = { onPetSelected(PetType.GREEN) }
            )

            PetCard(
                title = "Orange Pet",
                color = Color(0xFFF97316),
                isSelected = uiState.petType == PetType.ORANGE,
                onClick = { onPetSelected(PetType.ORANGE) }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Дайте питомцу имя",
            fontSize = 12.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = uiState.name,
            onValueChange = onNameChange,
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
            onClick = onCreatePet,
            enabled = uiState.name.isNotBlank() && uiState.petType != null,
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
