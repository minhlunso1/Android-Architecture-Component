package minhna.android.androidarchitecturecomponent.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import minhna.android.androidarchitecturecomponent.ui.fragment.FirstFragment
import minhna.android.androidarchitecturecomponent.ui.observer.LogObserver

class MainActivity : AppCompatActivity(), LogObserver.Callback {

    lateinit var logObserver: LogObserver;

    override fun onResult(result: String) {
        Toast.makeText(this,  result, Toast.LENGTH_SHORT).show();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logObserver = LogObserver(this.javaClass.simpleName, lifecycle, this)
        lifecycle.addObserver(logObserver)
    }

    override fun onStart() {
        super.onStart()
        logObserver.execute()// this one execute before STARTED
    }

    override fun onResume() {
        super.onResume()
        logObserver.execute()
        supportFragmentManager.beginTransaction().add(android.R.id.content, FirstFragment()).commit()
    }
}
