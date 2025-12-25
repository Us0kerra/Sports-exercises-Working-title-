package com.example.fitpet.ui.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitpet.ui.statistics.components.Calendar

@Composable
fun StatisticsScreen(viewModel: StatisticsViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "Статистика сегодня", //TODO Если дата не сегодня -> Статистика ДАТА
            fontSize = 18.sp,
            color = Color(0xFF9810FA)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Ваш ежедневный прогресс",
            fontSize = 14.sp,
            color = Color(0xFF6B7280)
        )

        //ккал мин кол-во тренировок
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            //TODO: Иконка огня
            Text(
                text = "320", //TODO: Сделать хранилище откуда мы вытягиваеем значение
                fontSize = 14.sp,
                color = Color(0xFF9810FA)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "ккал",
                fontSize = 14.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            //ToDO: Иконка часов
            Text(
                text = "45", //TODO:Подтягивать из хранилища
                fontSize = 14.sp,
                color = Color(0xFF6B7280) //TODO: Какой-то бардовый

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Минут",
                fontSize = 14.sp,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
                //TODO: Иконка гантели
            Text(
                text = "3", //TODO: Хранилище
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Разминок",
                fontSize = 14.sp,
                color = Color.Black
            )
        }



        // Календарь прогресса

        Text(
            text = "Календарь прогресса",
            fontSize = 18.sp,
            color = Color(0xFF9810FA)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Отслеживайте свой путь тренировок",
            fontSize = 14.sp,
            color = Color(0xFF6B7280)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Icon(
                    ImageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Этот месяц",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "{n} день/дня/дней",
                    fontsize = 14.sp,
                    color = Color(0xFF9810FA)
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Icon(
                    ImageVector = Icons.Default, // TODO график вверх
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Текущая серия",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "{n} день/дня/дней",
                    fontsize = 14.sp,
                    color = Color(0xFF9810FA)
                )
            }
        }



        Spacer(modifier = Modifier.width(8.dp))

        Card {  }

        // Интерактивный календарь

        //TODO:Календарик

        Calendar()

        //Легенда

        //#TODO:Круглешок рядом с текстом
        Text(
            text = "Выполнено",
            fontSize = 14.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = "Сегодня",
            fontSize = 14.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = "Выбрано",
            fontSize = 14.sp,
            color = Color(0xFF6B7280)
        )

    }
}