package minhna.android.androidarchitecturecomponent.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import minhna.android.androidarchitecturecomponent.model.Account

/**
 * Created by minhnguyen on 12/1/17.
 */

class AccountViewModel: ViewModel() {

    private val account = MutableLiveData<Account>()

    fun setAccount(account: Account) {
        this.account.value = account
    }
    fun getAccount(): LiveData<Account> {
        return account
    }

}