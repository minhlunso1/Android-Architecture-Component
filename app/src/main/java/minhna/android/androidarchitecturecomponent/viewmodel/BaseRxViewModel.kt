package minhna.android.androidarchitecturecomponent.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

open class BaseRxViewModel: ViewModel() {
    var compositeDisposable = CompositeDisposable()
    fun unSubscribe() = compositeDisposable.clear()

    fun addSubscription(obs: Observable<Any>, success: Consumer<Any>, error: Consumer<Throwable>) =
        compositeDisposable.add(obs.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(success, error))
}