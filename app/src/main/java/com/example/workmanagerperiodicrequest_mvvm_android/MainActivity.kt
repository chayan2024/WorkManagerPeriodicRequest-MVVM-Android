package com.example.workmanagerperiodicrequest_mvvm_android
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.workmanagerperiodicrequest_mvvm_android.ui.theme.WorkManagerPeriodicRequestMVVMAndroidTheme
import com.example.workmanagerperiodicrequest_mvvm_android.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkManagerPeriodicRequestMVVMAndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    LaunchedEffect(Unit) {
                        viewModel.startPeriodicWork()
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val name = "Channel Name"
                        val descriptionText = "Channel Description"
                        val importance = NotificationManager.IMPORTANCE_HIGH
                        val channel = NotificationChannel("channel_id", name, importance).apply {
                            description = descriptionText
                        }
                        val notificationManager: NotificationManager =
                            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.createNotificationChannel(channel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WorkManagerPeriodicRequestMVVMAndroidTheme {
        Greeting("Android")
    }
}