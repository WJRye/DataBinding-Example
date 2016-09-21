package com.wj.databinding;

import android.view.View;

/**
 * Author：王江 on 2016/9/21 11:49
 * Description:
 */
public interface IViewDataBinding {
    /**
     * @return 布局id
     */
    int getLayoutId();

    /**
     * 点击事件
     *
     * @param v
     */
    void onClick(View v);

    /**
     * 设置监听器
     */
    void setListener();
}
