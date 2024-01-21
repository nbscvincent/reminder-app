package com.nbscollege_jenjosh.schdulix
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters


class notificationReminder(
    context: Context,
    workerParameters: WorkerParameters
): Worker(context, workerParameters){
    public var notificationId = 17

    @SuppressLint("MissingPermission")
    override fun doWork(): Result {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val headerID =  inputData.getString("ID")
        val headerName =  inputData.getString("NAME")
        val headerMessage =  inputData.getString("MESSAGE")

        val channelId = "Schedulix_12"
        if (headerID != null) {
            notificationId = headerID.toInt()
        }

        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.schdulix_logo)
            .setContentTitle(headerName)
            .setContentText(headerMessage)
            .setStyle(NotificationCompat.BigTextStyle().bigText(headerMessage))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(notificationId, builder.build())
        }
        return Result.success()
    }
}