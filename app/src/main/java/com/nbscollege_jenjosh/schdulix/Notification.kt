package com.nbscollege_jenjosh.schdulix
import android.annotation.SuppressLint
import android.app.Notification.DEFAULT_ALL
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.getActivity
import android.app.job.JobInfo.PRIORITY_MAX
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Color.RED
import android.os.Build.VERSION.SDK_INT
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters


class notificationReminder(
    context: Context,
    workerParameters: WorkerParameters
): Worker(context, workerParameters){
    var notificationId = 17

    @SuppressLint("MissingPermission")
    override fun doWork(): Result {

        // notification here
        val headerID =  inputData.getString("ID")
        val headerName =  inputData.getString("NAME")
        val headerMessage =  inputData.getString("MESSAGE")

        val notifId = headerID
        val notifChannel = "SCH_CHANNEL"
        val notifName = "SCH_CHANNEL_NAME"
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(notifId, notificationId)

        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val pendingIntent = getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(applicationContext, notifChannel)
            .setSmallIcon(R.drawable.schdulix_logo)
            .setContentTitle(headerName).setContentText(headerMessage)
            .setDefaults(DEFAULT_ALL).setContentIntent(pendingIntent).setAutoCancel(true)

        notification.priority = PRIORITY_MAX

        if (SDK_INT >= 0) {
            notification.setChannelId(notifChannel)

            val channel = NotificationChannel(notifChannel, notifName, IMPORTANCE_HIGH)

            channel.enableLights(true)
            channel.lightColor = RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            //channel.setSound(ringtoneManager, audioAttributes)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(notificationId, notification.build())
        // end notification here

        return Result.success()
    }
}