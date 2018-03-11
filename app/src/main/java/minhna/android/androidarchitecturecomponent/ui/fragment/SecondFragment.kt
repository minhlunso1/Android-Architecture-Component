package minhna.android.androidarchitecturecomponent.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import minhna.android.androidarchitecturecomponent.R
import minhna.android.androidarchitecturecomponent.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.experimental.launch
import minhna.android.androidarchitecturecomponent.model.Account
import minhna.android.androidarchitecturecomponent.util.UI
import minhna.android.androidarchitecturecomponent.util.Util
import minhna.android.androidarchitecturecomponent.util.inflate

/**
 * Created by minhnguyen on 12/1/17.
 */

class SecondFragment : BaseFragment() {

    lateinit var model: AccountViewModel

    var observerAccount : Observer<Account> = Observer { account ->
        account?.let {
            tv_card_number.text = account.getFormatCardNumber()

            launch (UI) {
                val bm: Bitmap? = Util.encodeAsBitmap(activity, account.cardNumber.toString()).await()
                img_qr.visibility = View.VISIBLE
                img_qr.setImageBitmap(bm)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_second)
    }

    override fun onResume() {
        super.onResume()
        //both observer on First and Second Fragment will execute
//        Handler().postDelayed({
//            val account1 = Account(0, 1234)
//            model.setAccount(account1.copy(cardNumber = 1111))
//        }, 1000)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = ViewModelProviders.of(activity).get(AccountViewModel::class.java)
        model.getAccount().observe(activity, observerAccount)
    }

    override fun onDestroy() {
        super.onDestroy()
        model.getAccount().removeObserver(observerAccount)
    }

}