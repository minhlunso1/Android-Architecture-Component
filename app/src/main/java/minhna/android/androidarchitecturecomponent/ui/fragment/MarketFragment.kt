package minhna.android.androidarchitecturecomponent.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_market.*
import minhna.android.androidarchitecturecomponent.R
import minhna.android.androidarchitecturecomponent.util.inflate
import minhna.android.androidarchitecturecomponent.util.toast
import minhna.android.androidarchitecturecomponent.viewmodel.ListCoinMarketViewModel

/**
 * Created by minhnguyen on 3/8/18.
 */
class MarketFragment : BaseFragment() {
    lateinit var viewModel: ListCoinMarketViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListCoinMarketViewModel::class.java)
        viewModel.init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_market)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv.layoutManager = LinearLayoutManager(context)
        rv.setHasFixedSize(true)

        viewModel.list?.observe(this, Observer {
            list ->
            list?.let {
                context.toast(list.size.toString())
            }
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.unSubscribe()
    }
}