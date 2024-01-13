package com.nbscollege_jenjosh.schdulix

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

class Notification(var context: Context,var title: String, var msg: String) {
    val channelId: String = "Schdulix12345"
    val channelName: String = "Schdulix_Name"
    //val notificationManager = context.applicationContext.getSystemService(Context)

    /*val channelId = "Schedulix_12"
    var builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.schdulix_logo)
        .setContentTitle("My notification")
        .setContentText("Much longer text that cannot fit one line...")
        .setStyle(
            NotificationCompat.BigTextStyle()
            .bigText("Much longer text that cannot fit one line..."))
        .setPriority(NotificationCompat.PRIORITY_HIGH)


    val channel = NotificationChannel(channelId, "Schdulix", NotificationManager.IMPORTANCE_DEFAULT).apply {
        description = "SAMPLE"
    }
    val notificationManager: NotificationManager =
        context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
    notificationManager.notify(100, builder.build())*/
}