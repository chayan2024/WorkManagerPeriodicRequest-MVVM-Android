package com.example.workmanagerperiodicrequest_mvvm_android.viewmodel
import androidx.work.PeriodicWorkRequestBuilder
import com.example.workmanagerperiodicrequest_mvvm_android.utils.PeriodicWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workManager: WorkManager
) : ViewModel() {

    fun startPeriodicWork() {

        val periodicWorkRequest = PeriodicWorkRequestBuilder<PeriodicWorker>(10, TimeUnit.SECONDS)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "MyPeriodicWork",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }

}
