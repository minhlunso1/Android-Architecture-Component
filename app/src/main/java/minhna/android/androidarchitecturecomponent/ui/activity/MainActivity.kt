package minhna.android.androidarchitecturecomponent.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.Toast
import minhna.android.androidarchitecturecomponent.R
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

    fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack)
            clearBackStack()
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.replace(android.R.id.content, f)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}
