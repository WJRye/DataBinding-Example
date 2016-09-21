package com.wj.databinding.module.main;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wj.databinding.IViewDataBinding;

/**
 * Author：王江 on 2016/9/12 20:33
 * Description:
 */
public abstract class BaseFragment extends Fragment implements IViewDataBinding {

    public static final int TYPE_CALL_LOGS = 1;
    public static final int TYPE_CONTACTS = 2;
    public static final int TYPE_DIAL = 3;
    private ViewDataBinding mViewDataBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        setListener();
        return mViewDataBinding.getRoot();
    }

    public final ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void setListener() {

    }
}
