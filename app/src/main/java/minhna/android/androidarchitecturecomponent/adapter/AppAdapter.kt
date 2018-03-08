package minhna.android.androidarchitecturecomponent.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import minhna.android.androidarchitecturecomponent.R
import minhna.android.androidarchitecturecomponent.model.CoinMarket

/**
 * Created by minhnguyen on 3/8/18.
 */
class AppAdapter(var list: List<ViewType>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ViewType>
    private var delegateAdapters = SparseArrayCompat<DelegateAdapter>()

    init {
        this.items = list
        delegateAdapters.put(R.layout.item_market, MarketAdapter())
        delegateAdapters.put(R.layout.item_market_no_alias, MarketNoAliasAdapter())
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    fun getMarketNoAlias(): List<CoinMarket> {
        return items
                .filter { it.getViewType() == R.layout.item_market }
                .map { it as CoinMarket }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}