package minhna.android.androidarchitecturecomponent.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

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

    operator fun ViewGroup.get(pos: Int): View = getChildAt(pos)
    //usage
    //val view = viewGroup[2]

    //view.snack("Welcome", Snackbar.LENGTH_SHORT, {Toast.makeText(getThis(), "Snack", Toast.LENGTH_SHORT).show()})
    inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
        val snack = Snackbar.make(this, message, length)
        snack.f()
        snack.show()
    }

    fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
        setAction(action, listener)
        color?.let { setActionTextColor(color) }
    }
}