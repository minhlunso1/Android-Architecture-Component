package minhna.android.androidarchitecturecomponent.ui.activity

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.time.*
import androidx.view.*
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
        testView()
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

    private fun testView() {
        var view = View(this)

        view.postDelayed(delayInMillis = 200) {
            // some action
        }
        view.postOnAnimationDelayed(delayInMillis = 200) {
            // some action
        }

        view.setPadding(16)
        view.updatePadding(left = 16, right = 16, top = 16, bottom = 16)
        view.updatePaddingRelative(start = 16, end = 16, top = 16, bottom = 16)//Padding relative supports bot LTR & RTL

        //val bitmap = view.toBitmap(config = bitmapConfig)
    }
}