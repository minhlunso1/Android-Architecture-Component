package minhna.android.androidarchitecturecomponent.ui.activity

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.time.*
import java.time.Duration
import java.time.LocalDateTime

/**
 * Created by Minh on 3/31/2018.
 */
class TestActivity: BaseActivity() {
    val TAG = "TestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testDuration()
        testLocalDateTime()
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun testLocalDateTime() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val (localDate, localTime) = LocalDateTime.now()
            Log.d(TAG, logClickAble.prependCallLocation("Local Date: " + localDate))
            Log.d(TAG, logClickAble.prependCallLocation("Local Time: " + localTime))
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun testDuration() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val (seconds, nanoseconds) = Duration.ofSeconds(1, 1)
            val (seconds2, nanoseconds2) = Duration.ofSeconds(1) * 2
            val message = "Seconds: " + seconds + "\nNanoseconds: " + nanoseconds
            val message2 = "Seconds: " + seconds2 + "\nNanoseconds: " + nanoseconds2
            Log.d(TAG, logClickAble.prependCallLocation(message))
            Log.d(TAG, logClickAble.prependCallLocation(message2))
        }
    }
}