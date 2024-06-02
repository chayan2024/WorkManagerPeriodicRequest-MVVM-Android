package com.example.workmanagerperiodicrequest_mvvm_android.utils
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanagerperiodicrequest_mvvm_android.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PeriodicWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    private fun showNotification() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = 1
        val notification = NotificationCompat.Builder(applicationContext, "channel_id")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("Periodic Task")
            .setContentText("This is a notification from the periodic task.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(notificationId, notification)
    }
}
