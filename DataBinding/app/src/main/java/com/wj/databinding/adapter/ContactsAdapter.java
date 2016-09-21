package com.wj.databinding.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wj.databinding.BR;
import com.wj.databinding.R;
import com.wj.databinding.databinding.ContactsItemBinding;
import com.wj.databinding.model.Contact;
import com.wj.databinding.module.main.MainActivity;

import java.util.List;

/**
 * Author：王江 on 2016/9/14 15:26
 * Description:
 */
public class ContactsAdapter extends RecyclerView.Adapter {
    private MainActivity mContext;
    private List<Contact> mContactList;

    public ContactsAdapter(MainActivity context, List<Contact> contactList) {
        mContext = context;
        mContactList = contactList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.contacts_item, parent, false).getRoot();
        return new ViewCache(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new MenuDialogFragment().show(mContext.getSupportFragmentManager(), Integer.toString(holder.getAdapterPosition()));
                return true;
            }
        });
        Contact contact = mContactList.get(position);
        ViewCache viewCache = (ViewCache) holder;
        viewCache.getBinding().setVariable(BR.contact, contact);
        viewCache.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public static class ViewCache extends RecyclerView.ViewHolder {
        ContactsItemBinding binding;

        public ViewCache(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ContactsItemBinding getBinding() {
            return binding;
        }
    }


    private class MenuDialogFragment extends AppCompatDialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setItems(R.array.menu, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int location = Integer.parseInt(getTag());
                            switch (which) {
                                case 0:
                                    notifyItemMoved(location, 0);
                                    Contact contact = mContactList.remove(location);
                                    mContactList.add(0, contact);
                                    break;
                                case 1:
                                    notifyItemRemoved(location);
                                    mContactList.remove(location);
                                    break;
                            }

                        }
                    })
                    .create();
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            setStyle(STYLE_NO_TITLE, 0);
        }
    }

}
