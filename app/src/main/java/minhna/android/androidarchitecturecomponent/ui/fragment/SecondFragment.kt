package minhna.android.androidarchitecturecomponent.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import minhna.android.androidarchitecturecomponent.R
import minhna.android.androidarchitecturecomponent.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import minhna.android.androidarchitecturecomponent.util.Util


/**
 * Created by minhnguyen on 12/1/17.
 */

class SecondFragment : Fragment() {

    lateinit var model: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProviders.of(activity).get(AccountViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_second, container, false);
    }

    override fun onStart() {
        super.onStart()
        model.getAccount().observe(activity, Observer { account ->
            account?.let {
                tv_card_number.text = account.getFormatCardNumber()

                val bm: Bitmap? = Util.encodeAsBitmap(activity, account.cardNumber.toString())
                img_qr.visibility = View.VISIBLE
                img_qr.setImageBitmap(bm)
            }
        })
    }

}