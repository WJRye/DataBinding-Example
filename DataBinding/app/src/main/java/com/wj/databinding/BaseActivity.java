package com.wj.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Author：王江 on 2016/9/12 11:35
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity implements IViewDataBinding {

    private ViewDataBinding mViewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        setListener();
    }

    public final ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }

}
