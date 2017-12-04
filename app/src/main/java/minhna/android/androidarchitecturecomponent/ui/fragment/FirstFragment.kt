package minhna.android.androidarchitecturecomponent.ui.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import minhna.android.androidarchitecturecomponent.R
import android.arch.lifecycle.ViewModelProviders
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import minhna.android.androidarchitecturecomponent.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import minhna.android.androidarchitecturecomponent.model.Account


/**
 * Created by minhnguyen on 12/1/17.
 */

class FirstFragment: Fragment() {

    var root: View? = null
    lateinit var model: AccountViewModel
    val creditPartNumber = 4;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProviders.of(activity).get(AccountViewModel::class.java)
        model.getAccount().observe(activity, Observer { account ->
            Log.v(this.javaClass.simpleName, account?.cardNumber.toString())
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater?.inflate(R.layout.fragment_first, container, false);
        return root
    }

    override fun onStart() {
        super.onStart()
        btn_generate.setOnClickListener({
            if ((edt_card1.getText().toString().isBlank() || edt_card2.getText().toString().isBlank() ||
                    edt_card3.getText().toString().isBlank() || edt_card4.getText().toString().isBlank())
                    || (edt_card1.getText().toString().length < 4 || edt_card2.getText().toString().length < 4 ||
                    edt_card3.getText().toString().length < 4 || edt_card4.getText().toString().length < 4))
                tv_error.visibility = View.VISIBLE
            else {
                tv_error.visibility = View.INVISIBLE

                var cardNumber = edt_card1.getText().toString() + edt_card2.getText().toString() +
                        edt_card3.getText().toString() + edt_card4.getText().toString()


                model.setAccount(Account(0, cardNumber.toLong()))
                activity.supportFragmentManager.beginTransaction()
                        .replace(android.R.id.content, SecondFragment())
                        .addToBackStack(null)
                        .commit()
            }
        })

        edt_card1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length == creditPartNumber)
                    edt_card2.requestFocus()
            }

            override fun afterTextChanged(s: Editable) {}
        })
        edt_card2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length == creditPartNumber)
                    edt_card3.requestFocus()
            }

            override fun afterTextChanged(s: Editable) {}
        })
        edt_card3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.length == creditPartNumber)
                    edt_card4.requestFocus()
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

}