package minhna.android.androidarchitecturecomponent.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.functions.Consumer
import minhna.android.androidarchitecturecomponent.api.ManagerAPI
import minhna.android.androidarchitecturecomponent.model.CoinMarket

/**
 * Created by minhnguyen on 3/8/18.
 */
class ListCoinMarketRxViewModel: BaseRxViewModel() {

    var managerAPI = ManagerAPI()

    var list: LiveData<List<CoinMarket>>? = null

    fun init() {
        list = getItems()
    }

    fun getItems(): LiveData<List<CoinMarket>> {
        val data = MutableLiveData<List<CoinMarket>>()
        addSubscription(managerAPI.getCoinMarket(), Consumer { items -> data.value = items as List<CoinMarket>},
                Consumer { e -> e.printStackTrace() })
        return data
    }

}