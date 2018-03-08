package minhna.android.androidarchitecturecomponent.model

import minhna.android.androidarchitecturecomponent.R
import minhna.android.androidarchitecturecomponent.adapter.ViewType

/**
 * Created by minhnguyen on 3/8/18.
 */
data class CoinMarket(val product_code: String, val alias: String): ViewType {
    override fun getViewType(): Int {
        if (alias == null  || alias.isEmpty())
            return R.layout.item_market_no_alias
        else
            return R.layout.item_market
    }
};