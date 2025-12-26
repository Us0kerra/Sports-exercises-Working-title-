package com.example.fitpet.data.achievements

import com.example.fitpet.R

object AchievementsRepository {
    private val achievements = mutableListOf(
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

    fun getAchievements(): List<Achievement> {
        return achievements.toList()
    }

    fun claimAchievement(id: Int) {
        val index = achievements.indexOfFirst { it.id == id }
        if (index != -1) {
            val achievement = achievements[index]
            if (achievement.status == AchievementStatus.DONE_NOT_CLAIMED) {
                achievements[index] = achievement.copy(status = AchievementStatus.DONE_CLAIMED)
            }
        }
    }
}
