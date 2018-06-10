package minhna.android.androidarchitecturecomponent.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_market.view.*
import minhna.android.androidarchitecturecomponent.R
import minhna.android.androidarchitecturecomponent.model.CoinMarket
import minhna.android.androidarchitecturecomponent.util.inflate

/**
 * Created by minhnguyen on 3/8/18.
 */
class MarketAdapter: DelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = MarketViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MarketViewHolder
        holder.bind(item as CoinMarket)
    }

    class MarketViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_market)) {

        fun bind(item: CoinMarket) = with(itemView) {
            val text = "Market: " + item.productCode + " - alias: " + item.alias
            tvMarket.text = text
        }
    }

}