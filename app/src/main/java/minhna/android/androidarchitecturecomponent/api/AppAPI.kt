package minhna.android.androidarchitecturecomponent.api

import minhna.android.androidarchitecturecomponent.model.CoinMarket
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by minhnguyen on 3/8/18.
 */
interface AppAPI {

    @GET(UrlAPI.marketAPI)
    fun getCoinMarket(): Call<List<CoinMarket>>;

}