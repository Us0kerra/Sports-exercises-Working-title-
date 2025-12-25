package com.example.fitpet.ui.pet

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PetScreen(viewModel: PetViewModel){

    Text(
        text = "Добро пожаловать!",
        fontsize = 16.sp,
        color = Color(0xFF9810FA)
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Выберите своего питомца и дайте ему имя",
        fontSize = 12.sp,
        color = Color(0xFF6B7280)
    )

    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = "Выберите питомца",
        fontSize = 14.sp,
        color = Color.Black
    )

    Row(
       modifier = Modifier
           .fillMaxWidth()
           .padding(14.dp)
    ){
        Card(){
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Cyan, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(ImageVector = Icons.Default)
            }

            Spacer(modifier = Modifier.height( 14.dp))

            Text(
                text = "Синий питомец",
                fontSize = 12.sp
            )

        }

        Spacer(modifier = Modifier.width(12.dp))

        Card(){
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Green, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(ImageVector = Icons.Default) //TODO ШАГИ ЖИВТОНОГО
            }

            Spacer(modifier = Modifier.height( 14.dp))

            Text(
                text = "Зеленый питомец",
                fontSize = 12.sp
            )

        }


        Card(){
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.Yellow, CircleShape), //TODO Orange
                contentAlignment = Alignment.Center
            ) {
                Icon(ImageVector = Icons.Default)
            }

            Spacer(modifier = Modifier.height( 14.dp))

            Text(
                text = "Оранжевый питомец",
                fontSize = 12.sp
            )

        }


    }

    Spacer(modifier = Modifier.height(14.dp))

    Text(
        text = "Дайте имя питомцу",
        fontsize = 12.sp
    )

    // TODO inputbox




}