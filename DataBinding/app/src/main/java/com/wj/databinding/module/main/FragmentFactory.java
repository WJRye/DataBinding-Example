package com.wj.databinding.module.main;

/**
 * Author：王江 on 2016/9/12 20:36
 * Description:
 */
public class FragmentFactory {

    private FragmentFactory() {
    }

    public static BaseFragment getInstance(int type) {
        BaseFragment fragment = null;
        switch (type) {
            case BaseFragment.TYPE_CALL_LOGS:
                fragment = new CalllogsFragment();
                break;
            case BaseFragment.TYPE_CONTACTS:
                fragment = new ContactsFragment();
                break;
            case BaseFragment.TYPE_DIAL:
                fragment = new DialFragment();
                break;
            default:
                break;
        }

        return fragment;
    }
}
