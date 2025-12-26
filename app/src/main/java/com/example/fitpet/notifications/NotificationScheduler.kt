package com.example.fitpet.notifications

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import java.util.Calendar

object NotificationScheduler {

    const val CHANNEL_ID = "daily_reminder_channel"
    const val EXTRA_HOUR = "extra_hour"
    const val EXTRA_MINUTE = "extra_minute"
    const val EXTRA_INDEX = "extra_index"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Напоминания о тренировках",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Ежедневные напоминания о тренировках с питомцем"
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun scheduleDailyReminders(context: Context, times: List<String>) {
        createNotificationChannel(context)
        
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        
        // Отменяем все предыдущие уведомления
        cancelAllDailyReminders(context)
        
        // Планируем новые уведомления
        times.forEachIndexed { index, timeString ->
            val (hour, minute) = timeString.split(":").let {
                val h = it.getOrNull(0)?.toIntOrNull() ?: 9
                val m = it.getOrNull(1)?.toIntOrNull() ?: 0
                h to m
            }
            
            scheduleSingleReminder(context, alarmManager, hour, minute, index)
        }
    }
    
    fun scheduleSingleReminder(
        context: Context,
        alarmManager: AlarmManager,
        hour: Int,
        minute: Int,
        index: Int
    ) {
        val pendingIntent = createPendingIntent(context, hour, minute, index)
        
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            // если время уже прошло сегодня — переносим на завтра
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        // Используем setExactAndAllowWhileIdle для Android 6.0+ вместо setRepeating
        // Это более надежный метод, который работает даже когда устройство в режиме Doze
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            } catch (e: SecurityException) {
                // Fallback для случаев, когда нет разрешения SCHEDULE_EXACT_ALARM
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            }
        } else {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }

    fun cancelAllDailyReminders(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // Отменяем все возможные уведомления (до 5)
        for (i in 0 until 5) {
            // Создаем PendingIntent с любыми параметрами для отмены
            val intent = Intent(context, DailyReminderReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                i,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.cancel(pendingIntent)
        }
    }

    @Deprecated("Use scheduleDailyReminders instead")
    fun scheduleDailyReminder(context: Context, hour: Int, minute: Int) {
        scheduleDailyReminders(context, listOf(String.format("%02d:%02d", hour, minute)))
    }

    @Deprecated("Use cancelAllDailyReminders instead")
    fun cancelDailyReminder(context: Context) {
        cancelAllDailyReminders(context)
    }

    private fun createPendingIntent(
        context: Context,
        hour: Int,
        minute: Int,
        index: Int
    ): PendingIntent {
        val intent = Intent(context, DailyReminderReceiver::class.java).apply {
            putExtra(EXTRA_HOUR, hour)
            putExtra(EXTRA_MINUTE, minute)
            putExtra(EXTRA_INDEX, index)
        }
        return PendingIntent.getBroadcast(
            context,
            index,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}


