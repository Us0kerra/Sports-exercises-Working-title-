package com.example.fitpet.data.achievements

import com.example.fitpet.R
import androidx.compose.material.icons.Icons


class AchievementsRepository {
    fun getAchievements(): List<Achievement> {
        return listOf(
            Achievement(
                id = 1,
                title = "Первый шаг",
                description = "Сделайте первую разминку",
                rewardIcon = R.drawable.apparel,
                rewardName = "Шапка питомца",
                //typeIcon = R.drawable.ic_step,#TODO: Иконка достижения
                typeIcon = R.drawable.ic_pet_black_24dp,
                progress = 1f,
                status = AchievementStatus.DONE_CLAIMED
            ),
            Achievement(
                id = 2,
                title = "Регулярный пользователь",
                description = "Разминка каждый день неделю",
                rewardIcon = R.drawable.apparel,
                rewardName = "Очки питомца",
//                typeIcon = R.drawable.ic_calendar,
                typeIcon = R.drawable.ic_pet_black_24dp,
                progress = 1f,
                status = AchievementStatus.DONE_NOT_CLAIMED
            ),
            Achievement(
                id = 3,
                title = "Мастер разминок",
                description = "Завершите 50 разминок",
                rewardIcon = R.drawable.apparel,
                rewardName = "Фон питомца",
//                typeIcon = R.drawable.ic_trophy,
                typeIcon = R.drawable.ic_pet_black_24dp,
                progress = 0.5f,
                status = AchievementStatus.NOT_DONE
            )
        )
    }
}
