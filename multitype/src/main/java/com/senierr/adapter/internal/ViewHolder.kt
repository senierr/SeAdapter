package com.senierr.adapter.internal

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewSparseArray: SparseArray<View> = SparseArray()

    @Suppress("UNCHECKED_CAST")
    fun <T : View> findView(@IdRes viewId: Int): T? {
        var view: View? = viewSparseArray.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            viewSparseArray.put(viewId, view)
        }
        return view as T?
    }

    companion object {
        fun create(parent: ViewGroup, @LayoutRes layoutId: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
            return create(itemView)
        }

        fun create(layoutInflater: LayoutInflater, parent: ViewGroup, @LayoutRes layoutId: Int): ViewHolder {
            val itemView = layoutInflater.inflate(layoutId, parent, false)
            return create(itemView)
        }

        fun create(itemView: View): ViewHolder {
            return ViewHolder(itemView)
        }
    }
}
