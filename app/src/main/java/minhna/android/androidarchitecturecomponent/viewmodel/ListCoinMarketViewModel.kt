package minhna.android.androidarchitecturecomponent.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import minhna.android.androidarchitecturecomponent.api.ManagerAPI
import minhna.android.androidarchitecturecomponent.model.CoinMarket

/**
 * Created by minhnguyen on 3/8/18.
 */
class ListCoinMarketViewModel: ViewModel() {

    var compositeDisposable = CompositeDisposable()
    var managerAPI = ManagerAPI()

    var list: LiveData<List<CoinMarket>>? = null

    fun init() {
        list = getItems()
    }

    fun getItems(): LiveData<List<CoinMarket>> {
        val data = MutableLiveData<List<CoinMarket>>()

        compositeDisposable.add(
                managerAPI.getCoinMarket()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            items ->
                            data.value = items
                        }, {
                            e -> e.printStackTrace()
                        })
        )
        return data
    }

    fun unSubscribe() = compositeDisposable.dispose()

}