package com.senierr.adapter.support.wrapper

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.senierr.adapter.internal.ViewHolderWrapper
import com.senierr.adapter.support.bean.StateBean

/**
 * 状态封装
 *
 * @author zhouchunjie
 * @date 2017/10/9
 */
abstract class BaseStateWrapper(@LayoutRes private val layoutId: Int = -1) : ViewHolderWrapper<StateBean>(layoutId) {

    private val stateBean = StateBean()

    override fun getSpanSize(item: StateBean): Int = Integer.MAX_VALUE

    /**
     * 更新状态
     *
     * @param state
     */
    fun setState(state: Int) {
        stateBean.state = state
        recyclerView?.let {
            multiTypeAdapter.data.clear()
            multiTypeAdapter.data.add(stateBean)
            multiTypeAdapter.notifyDataSetChanged()
            val layoutManager = it.layoutManager
            if (layoutManager != null) {
                if (layoutManager is StaggeredGridLayoutManager) {
                    layoutManager.invalidateSpanAssignments()
                }
                layoutManager.scrollToPosition(0)
            }
        }
    }
}
