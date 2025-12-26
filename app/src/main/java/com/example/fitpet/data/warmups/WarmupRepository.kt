package com.example.fitpet.data.warmups

import com.example.fitpet.R

class WarmupRepository {

    fun getWarmups(): List<Warmup> {
        return warmups
    }

    companion object {
        private val exercises = listOf(
            Exercise(
                id = "1",
                name = "Растяжка шеи",
                description = "Аккуратно наклоняйте голову из стороны в сторону.",
                durationSeconds = 30,
                repetitions = 5,
                imageRes = R.drawable.cat_base,
                restSeconds = 10,
                caloriesPerMinute = 3
            ),
            Exercise(
                id = "2",
                name = "Вращение плеч",
                description = "Вращайте плечи вперёд и назад.",
                durationSeconds = 30,
                repetitions = 10,
                imageRes = R.drawable.cat_base,
                restSeconds = 10,
                caloriesPerMinute = 4
            ),
            Exercise(
                id = "3",
                name = "Растяжка «Кошка-Корова»",
                description = "Чередуйте прогиб и округление спины.",
                durationSeconds = 60,
                repetitions = 10,
                imageRes = R.drawable.cat_base,
                restSeconds = 10,
                caloriesPerMinute = 5
            ),
            Exercise(
                id = "4",
                name = "Собака мордой вниз",
                description = "Растяжка всего тела, особенно задней поверхности бедра, голеней и плеч.",
                durationSeconds = 60,
                repetitions = 3,
                imageRes = R.drawable.cat_base,
                restSeconds = 15,
                caloriesPerMinute = 6
            ),
            Exercise(
                id = "5",
                name = "Прыжки «Джек»",
                description = "Классическое кардио для повышения пульса.",
                durationSeconds = 60,
                repetitions = 20,
                imageRes = R.drawable.cat_base,
                restSeconds = 15,
                caloriesPerMinute = 10
            ),
            Exercise(
                id = "6",
                name = "Высокие колени",
                description = "Отличная разминка для ног и корпуса.",
                durationSeconds = 60,
                repetitions = 20,
                imageRes = R.drawable.cat_base,
                restSeconds = 15,
                caloriesPerMinute = 9
            ),
            Exercise(
                id = "7",
                name = "Бёдра назад",
                description = "Бег на месте с ударом пяткой по ягодицам, растягивает квадрицепсы.",
                durationSeconds = 60,
                repetitions = 20,
                imageRes = R.drawable.cat_base,
                restSeconds = 15,
                caloriesPerMinute = 8
            ),
            Exercise(
                id = "8",
                name = "Приседания с весом тела",
                description = "Базовое силовое упражнение для всей нижней части тела.",
                durationSeconds = 60,
                repetitions = 15,
                imageRes = R.drawable.cat_base,
                restSeconds = 20,
                caloriesPerMinute = 7
            ),
            Exercise(
                id = "9",
                name = "Выпады",
                description = "Эффективное упражнение для квадрицепсов, ягодиц и задней поверхности бедра.",
                durationSeconds = 60,
                repetitions = 10,
                imageRes = R.drawable.cat_base,
                restSeconds = 20,
                caloriesPerMinute = 8
            ),
            Exercise(
                id = "10",
                name = "Махи ногами",
                description = "Динамическая растяжка для разогрева бедер и задней поверхности бедра.",
                durationSeconds = 30,
                repetitions = 10,
                imageRes = R.drawable.cat_base,
                restSeconds = 10,
                caloriesPerMinute = 4
            )
        )

        private val warmups = listOf(
            Warmup(
                id = "1",
                name = "Разминка для всего тела",
                description = "Полный комплекс растяжки для всего тела.",
                type = WarmupType.GENERAL,
                exercises = exercises.slice(0..3)
            ),
            Warmup(
                id = "2",
                name = "Быстрое кардио",
                description = "Короткая кардиотренировка для разгона сердца.",
                type = WarmupType.HANDS,
                exercises = exercises.slice(4..6)
            ),
            Warmup(
                id = "3",
                name = "Разминка для ног",
                description = "Разминка, направленная на подготовку ног к тренировке.",
                type = WarmupType.LEGS,
                exercises = exercises.slice(7..9)
            )
        )

        fun getWarmupById(id: String?): Warmup? {
            return warmups.find { it.id == id }
        }
    }
}