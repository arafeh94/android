package com.arafeh.base.internal.core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.arafeh.base.R

/**
 * Created by Arafeh on 7/14/2018.
 */
abstract class BaseAdapter<T>(context: Context, var items: ArrayList<T> = ArrayList()) : ArrayAdapter<T>(context, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_report, null)
            val viewHolder = viewHolder(view, getItem(position))
            viewHolder.bind()
            view.tag = viewHolder
        } else {
            view = convertView
            val viewHolder = view.tag as BaseViewHolder<*>;
            viewHolder.reBind()
        }
        return view
    }


    abstract fun layout(): Int
    abstract fun viewHolder(view: View, item: T): BaseViewHolder<T>
}