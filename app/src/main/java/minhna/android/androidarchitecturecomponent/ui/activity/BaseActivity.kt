package minhna.android.androidarchitecturecomponent.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

/**
 * Created by minhnguyen on 2/13/18.
 */
open class BaseActivity: AppCompatActivity() {
    lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    fun getThis() = this;

    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }

    inline fun DrawerLayout.consume(f: () -> Unit): Boolean {
        f()
        closeDrawers()
//        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}