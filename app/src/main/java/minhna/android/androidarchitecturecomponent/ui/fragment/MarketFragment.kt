package minhna.android.androidarchitecturecomponent.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_market.*
import minhna.android.androidarchitecturecomponent.R
import minhna.android.androidarchitecturecomponent.adapter.AppAdapter
import minhna.android.androidarchitecturecomponent.util.inflate
import minhna.android.androidarchitecturecomponent.viewmodel.ListCoinMarketRxViewModel

/**
 * Created by minhnguyen on 3/8/18.
 */
class MarketFragment : SingleVMBaseFragment<ListCoinMarketRxViewModel>() {
    override fun createViewModel(): ListCoinMarketRxViewModel = ViewModelProviders.of(this).get(ListCoinMarketRxViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm?.init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_market)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv.layoutManager = LinearLayoutManager(context)
        rv.setHasFixedSize(true)

        vm?.list?.observe(this, Observer {
            list ->
            list?.let {
                rv.adapter = AppAdapter(list)
            }
        })
    }
}