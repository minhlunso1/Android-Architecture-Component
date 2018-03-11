package minhna.android.androidarchitecturecomponent.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_market_no_alias.view.*
import minhna.android.androidarchitecturecomponent.R
import minhna.android.androidarchitecturecomponent.model.CoinMarket
import minhna.android.androidarchitecturecomponent.util.inflate

/**
 * Created by minhnguyen on 3/8/18.
 */
class MarketNoAliasAdapter : DelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = MarketNoAliasViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as MarketNoAliasViewHolder
        holder.bind(item as CoinMarket)
    }

    class MarketNoAliasViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_market_no_alias)) {

        fun bind(item: CoinMarket) = with(itemView) {
            val text = "Market: " + item.product_code
            tvMarket.text = text
        }
    }
}