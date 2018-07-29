package minhna.android.androidarchitecturecomponent.model

import com.squareup.moshi.Json
import minhna.android.androidarchitecturecomponent.R
import minhna.android.androidarchitecturecomponent.adapter.ViewType

/**
 * Created by minhnguyen on 3/8/18.
 */
data class CoinMarket(@Json(name = "product_code") val productCode: String,
                      val alias: String): ViewType {
    constructor(): this(productCode = "", alias = "")//include this to avoid nullability
    constructor(pCode: String): this(productCode = pCode, alias = "")

    override fun getViewType(): Int {
        return if (alias.isEmpty())
            R.layout.item_market_no_alias
        else
            R.layout.item_market
    }
}