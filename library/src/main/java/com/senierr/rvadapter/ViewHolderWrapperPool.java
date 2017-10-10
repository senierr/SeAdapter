package com.senierr.rvadapter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 工人池（工人宿舍？）
 *
 * @author zhouchunjie
 * @date 2017/10/10
 */

public class ViewHolderWrapperPool {

    // 工人集合
    private List<ViewHolderWrapper<?>> viewHolderWrappers;

    public ViewHolderWrapperPool() {
        viewHolderWrappers = new ArrayList<>();
    }

    /**
     * 添加工人
     *
     * @param viewHolderWrappers
     */
    public void addViewHolderWrapper(@NonNull ViewHolderWrapper<?>... viewHolderWrappers) {
        for (ViewHolderWrapper<?> viewHolderWrapper : viewHolderWrappers) {
            this.viewHolderWrappers.add(viewHolderWrapper);
        }
    }

    /**
     * 获取处理该数据的工人的索引
     *
     * @param item 待处理数据
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> int getViewHolderWrapperIndex(@NonNull T item) {
        for (int i = 0; i < viewHolderWrappers.size(); i++) {
            ViewHolderWrapper<?> viewHolderWrapper = viewHolderWrappers.get(i);
            if (viewHolderWrapper.getDataCls().equals(item.getClass())) {
                if (((ViewHolderWrapper<T>) viewHolderWrapper).onAcceptAssignment(item)) {
                    return i;
                }
            }
        }
        throw new RuntimeException(String.format(
                "Can not found ViewHolderWrapper for %s!", item.getClass().getSimpleName()));
    }

    /**
     * 获取工人
     *
     * @param index
     * @return
     */
    public ViewHolderWrapper<?> getViewHolderWrapper(int index) {
        return viewHolderWrappers.get(index);
    }

    public List<ViewHolderWrapper<?>> getViewHolderWrappers() {
        return viewHolderWrappers;
    }

    public void setViewHolderWrappers(List<ViewHolderWrapper<?>> viewHolderWrappers) {
        this.viewHolderWrappers = viewHolderWrappers;
    }
}
