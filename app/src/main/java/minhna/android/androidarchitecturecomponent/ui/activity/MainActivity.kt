package minhna.android.androidarchitecturecomponent.ui.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import minhna.android.androidarchitecturecomponent.R
import minhna.android.androidarchitecturecomponent.ui.fragment.FirstFragment
import minhna.android.androidarchitecturecomponent.ui.fragment.MarketFragment
import minhna.android.androidarchitecturecomponent.ui.observer.LogObserver

class MainActivity : BaseActivity(), LogObserver.Callback, NavigationView.OnNavigationItemSelectedListener {

    lateinit var logObserver: LogObserver;

    private val toggle by lazy {
        object : ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerClosed(v: View?) {
                super.onDrawerClosed(v)
            }

            override fun onDrawerOpened(v: View?) {
                super.onDrawerOpened(v)
                try {
                    inputMethodManager.hideSoftInputFromWindow(getThis().currentFocus!!.windowToken, 0)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onResult(result: String) {
        Toast.makeText(this,  result, Toast.LENGTH_SHORT).show();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        logObserver = LogObserver(this.javaClass.simpleName, lifecycle, this)
        lifecycle.addObserver(logObserver)

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        logObserver.execute()// this one execute before STARTED
    }

    override fun onResume() {
        super.onResume()
        logObserver.execute()
        changeFragment(FirstFragment(), true)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else
            super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, resources.getText(R.string.not_avail), Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.example_1 -> drawer_layout.consume { changeFragment(FirstFragment(), true) }
        R.id.example_2 -> drawer_layout.consume { changeFragment(MarketFragment(), true) }
        R.id.example_3 -> consume {
            Toast.makeText(this, resources.getText(R.string.not_avail), Toast.LENGTH_SHORT).show()
        }
        else -> super.onOptionsItemSelected(item)
    }



}
