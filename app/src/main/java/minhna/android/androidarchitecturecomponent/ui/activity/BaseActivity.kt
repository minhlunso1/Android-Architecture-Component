package minhna.android.androidarchitecturecomponent.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import minhna.android.androidarchitecturecomponent.R
import minhna.android.androidarchitecturecomponent.util.LogClickAble

/**
 * Created by minhnguyen on 2/13/18.
 */
open class BaseActivity: AppCompatActivity() {
    lateinit var inputMethodManager: InputMethodManager
    val logClickAble by lazy {LogClickAble()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack)
            clearBackStack()
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.replace(R.id.main_activity_container, f)
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

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1)
            fragmentManager.popBackStack()
        else
            finish()
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