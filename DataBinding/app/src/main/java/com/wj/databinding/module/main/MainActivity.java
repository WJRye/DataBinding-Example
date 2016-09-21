package com.wj.databinding.module.main;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.wj.databinding.BaseActivity;
import com.wj.databinding.R;
import com.wj.databinding.databinding.MainBinding;
import com.wj.databinding.databinding.UserDetailBinding;
import com.wj.databinding.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public static final String USER = "USER";

    private final int CALL_LOGS = 0;
    private final int CONTACTS = 1;
    private final int DIAL = 2;

    private MainBinding mMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_48dp);

        mMainBinding = (MainBinding) getViewDataBinding();
        mMainBinding.mainPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        mMainBinding.mainCalllogs.setSelected(true);

        UserDetailBinding userDetailBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.drawer_layout, null, false);
        User user = getIntent().getParcelableExtra(USER);
        userDetailBinding.setUser(user);
        Bitmap portraitBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.me);
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), portraitBitmap);
        drawable.setFilterBitmap(true);
        drawable.setAntiAlias(true);
        drawable.setCircular(true);
        userDetailBinding.userPortrait.setImageDrawable(drawable);
        mMainBinding.mainNavigationView.addView(userDetailBinding.getRoot());

    }

    @Override
    public final int getLayoutId() {
        return R.layout.activity_main;
    }

    public final ViewPager.OnPageChangeListener addOnPageChangeListener() {

        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    private void setCurrentItem(int position) {
        switch (position) {
            case CALL_LOGS:
                mMainBinding.mainCalllogs.setSelected(true);
                mMainBinding.mainContacts.setSelected(false);
                mMainBinding.mainDial.setSelected(false);
                break;
            case CONTACTS:
                mMainBinding.mainCalllogs.setSelected(false);
                mMainBinding.mainContacts.setSelected(true);
                mMainBinding.mainDial.setSelected(false);
                break;
            case DIAL:
                mMainBinding.mainCalllogs.setSelected(false);
                mMainBinding.mainContacts.setSelected(false);
                mMainBinding.mainDial.setSelected(true);
                break;
            default:
                break;
        }
    }

    public final void onClick(View v) {
        if (mMainBinding.mainCalllogs.equals(v)) {
            setCurrentItem(CALL_LOGS);
            mMainBinding.mainPager.setCurrentItem(CALL_LOGS);
        } else if (mMainBinding.mainContacts.equals(v)) {
            setCurrentItem(CONTACTS);
            mMainBinding.mainPager.setCurrentItem(CONTACTS);
        } else if (mMainBinding.mainDial.equals(v)) {
            setCurrentItem(DIAL);
            mMainBinding.mainPager.setCurrentItem(DIAL);
        }
    }

    @Override
    public final void setListener() {
        ((MainBinding) getViewDataBinding()).setListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mMainBinding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mMainBinding.mainDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                mMainBinding.mainDrawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments = new ArrayList<>(3);

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments.add(FragmentFactory.getInstance(BaseFragment.TYPE_CALL_LOGS));
            mFragments.add(FragmentFactory.getInstance(BaseFragment.TYPE_CONTACTS));
            mFragments.add(FragmentFactory.getInstance(BaseFragment.TYPE_DIAL));
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
