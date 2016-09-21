package com.wj.databinding.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wj.databinding.BR;
import com.wj.databinding.R;
import com.wj.databinding.databinding.CallLogsItemBinding;
import com.wj.databinding.model.CallLog;
import com.wj.databinding.util.ContactUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：王江 on 2016/9/13 10:54
 * Description:
 */
public class CallLogsAdapter extends RecyclerView.Adapter {

    private List<CallLog> mCallLogs;
    /**
     * 缓存名字
     */
    private static Map<String, String> mCacheNames = new HashMap<>();

    public CallLogsAdapter(List<CallLog> callLogs) {
        mCallLogs = callLogs;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calllogs_item, parent, false);
        return new ViewCache(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CallLog callLog = mCallLogs.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + callLog.getNumber()));
                v.getContext().startActivity(intent);
            }
        });

        final ViewCache viewCache = (ViewCache) holder;
        viewCache.getBinding().setVariable(BR.callLog, callLog);
        viewCache.getBinding().addOnRebindCallback(new OnRebindCallback() {
            @Override
            public void onBound(ViewDataBinding binding) {
                if (TextUtils.isEmpty(callLog.getCacheName())) {
                    String name = mCacheNames.get(callLog.getNumber());
                    if (name == null) {
                        loadNameFromContact(viewCache.getBinding().callLogsName, callLog.getNumber());
                    } else {
                        viewCache.getBinding().callLogsName.setText(name);
                    }
                }
            }
        });

        viewCache.getBinding().executePendingBindings();

    }

    /**
     * 从联系人表中加载名字
     *
     * @param calllogsName 显示名字的View
     * @param number       电话号码
     */
    private static void loadNameFromContact(final AppCompatTextView calllogsName, final String number) {

        new AsyncTask<Void, String, String>() {
            Context context = calllogsName.getContext();

            @Override
            protected String doInBackground(Void... params) {
                return ContactUtil.getContactName(context, number);
            }

            @Override
            protected void onPostExecute(String name) {
                if (!TextUtils.isEmpty(name)) {
                    calllogsName.setText(name);
                    mCacheNames.put(number, name);
                } else {
                    mCacheNames.put(number, context.getResources().getString(R.string.stranger));
                }
            }
        }.execute();

    }


    @Override
    public int getItemCount() {
        return mCallLogs.size();
    }

    public static class ViewCache extends RecyclerView.ViewHolder {
        private CallLogsItemBinding binding;

        public ViewCache(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public CallLogsItemBinding getBinding() {
            return binding;
        }

    }


}
