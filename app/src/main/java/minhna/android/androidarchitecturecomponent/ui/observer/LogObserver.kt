package minhna.android.androidarchitecturecomponent.ui.observer

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log

/**
 * Created by minhnguyen on 12/1/17.
 */

open class LogObserver constructor(tag: String, lifecycle: Lifecycle, callback: Callback) : LifecycleObserver {
    val tag: String = tag
    val lifecycle: Lifecycle = lifecycle
    val callback: Callback = callback

    interface Callback {
        fun onResult(result : String)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.v(tag, "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.v(tag, "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.v(tag, "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.v(tag, "onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.v(tag, "onDestroy")
    }

    fun execute() {
        if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED))
            callback.onResult("executed")
    }
}