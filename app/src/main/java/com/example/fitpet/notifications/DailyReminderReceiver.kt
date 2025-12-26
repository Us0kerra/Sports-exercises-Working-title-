package com.example.fitpet.notifications

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.fitpet.MainActivity
import com.example.fitpet.R

class DailyReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        showNotification(context)
        
        // Планируем следующее уведомление на завтра
        val hour = intent.getIntExtra(NotificationScheduler.EXTRA_HOUR, 9)
        val minute = intent.getIntExtra(NotificationScheduler.EXTRA_MINUTE, 0)
        val index = intent.getIntExtra(NotificationScheduler.EXTRA_INDEX, 0)
        
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        NotificationScheduler.scheduleSingleReminder(context, alarmManager, hour, minute, index)
    }

    private fun showNotification(context: Context) {
        val channelId = NotificationScheduler.CHANNEL_ID
        val notificationId = 1001

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Создаём канал для Android 8+ (на случай если он еще не создан)
        NotificationScheduler.createNotificationChannel(context)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher) // используем иконку приложения
            .setContentTitle("FitPet")
            .setContentText("Пора позаниматься вместе с питомцем!")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationId, notification)
    }
}


