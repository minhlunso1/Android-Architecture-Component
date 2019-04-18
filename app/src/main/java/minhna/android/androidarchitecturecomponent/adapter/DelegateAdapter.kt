package minhna.android.androidarchitecturecomponent.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by minhnguyen on 3/8/18.
 */
interface DelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}