package com.wj.databinding.module.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wj.databinding.R;
import com.wj.databinding.adapter.CallLogsAdapter;
import com.wj.databinding.model.CallLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：王江 on 2016/9/12 20:37
 * Description:
 */
public class CalllogsFragment extends BaseFragment {

    private List<CallLog> mCallLogs;

    private CallLogsAdapter mAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCallLogs.clear();
            mCallLogs.addAll(getCallLogs());
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getContentResolver().registerContentObserver(android.provider.CallLog.Calls.CONTENT_URI, true, new ContentObserver(mHandler) {

            @Override
            public void onChange(boolean selfChange) {
                mHandler.sendEmptyMessage(0x123);
            }
        });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mCallLogs = getCallLogs();
        mAdapter = new CallLogsAdapter(mCallLogs);
        recyclerView.setAdapter(mAdapter);
    }

    @TargetApi(23)
    public List<CallLog> getCallLogs() {
        Cursor c = null;
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                c = getActivity().getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI, new String[]{android.provider.CallLog.Calls.CACHED_NAME, android.provider.CallLog.Calls.NUMBER, android.provider.CallLog.Calls.GEOCODED_LOCATION, android.provider.CallLog.Calls.DATE, android.provider.CallLog.Calls.TYPE}, null, null, android.provider.CallLog.Calls.DEFAULT_SORT_ORDER);
            }
        } else {
            c = getActivity().getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI, new String[]{android.provider.CallLog.Calls.CACHED_NAME, android.provider.CallLog.Calls.NUMBER, android.provider.CallLog.Calls.GEOCODED_LOCATION, android.provider.CallLog.Calls.DATE, android.provider.CallLog.Calls.TYPE}, null, null, android.provider.CallLog.Calls.DEFAULT_SORT_ORDER);
        }
        List<CallLog> callLogs = new ArrayList<>();
        if (c != null) {
            CallLog callLog = null;
            while (c.moveToNext()) {
                callLog = new CallLog();
                callLog.setCacheName(c.getString(0));
                callLog.setNumber(c.getString(1));
                callLog.setGeocodedLocation(c.getString(2));
                callLog.setDate(c.getLong(3));
                callLog.setType(c.getInt(4));
                callLogs.add(callLog);
            }
            c.close();
            c = null;
        }

        return callLogs;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_calllogs;
    }


}
