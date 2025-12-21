package com.example.fitpet.ui.achievements
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitpet.ui.settings.components.SettingSwitch

@Composable
fun AchievementsScreen(viewModel: AchievementsViewModel){


    Text(
        text = "Ваши достижения",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color(red=0x98, green=0x10, blue=0xfa, alpha=0xFF)
    )

    Spacer(Modifier.height(100.dp))

    Text(
        text = "Открывайте одежду и аксессуары для питомца",
        fontSize = 16.sp,
        color = Color.Gray
    )


    //Придумать как подгружать карточки с достижениями

}