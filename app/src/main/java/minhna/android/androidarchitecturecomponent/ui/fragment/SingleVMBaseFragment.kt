package minhna.android.androidarchitecturecomponent.ui.fragment

import android.os.Bundle
import minhna.android.androidarchitecturecomponent.viewmodel.BaseRxViewModel

/**
 * Created by Minh on 2/20/2018.
 */

abstract class SingleVMBaseFragment<VM: BaseRxViewModel>: BaseFragment() {
    protected var vm: VM? = null

    protected abstract fun createViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = createViewModel()
    }

    override fun onStop() {
        super.onStop()
        vm?.unSubscribe()
    }
}