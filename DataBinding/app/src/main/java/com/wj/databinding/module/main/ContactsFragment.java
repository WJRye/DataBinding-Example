package com.wj.databinding.module.main;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wj.databinding.R;
import com.wj.databinding.adapter.ContactsAdapter;
import com.wj.databinding.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：王江 on 2016/9/12 20:37
 * Description:
 */
public class ContactsFragment extends BaseFragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new ContactsAdapter((MainActivity) getActivity(), getContacts()));
    }


    private List<Contact> getContacts() {
        List<Contact> contactList = new ArrayList<>();
        ContentResolver cr = getActivity().getContentResolver();
        Cursor contactCursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID}, null, null,
                ContactsContract.Contacts.SORT_KEY_PRIMARY);//在raw_contacts表中得到contactId
        if (contactCursor != null)
            while (contactCursor.moveToNext()) {
                Contact contact = new Contact();
                long contactId = contactCursor.getLong(contactCursor
                        .getColumnIndex(ContactsContract.Contacts._ID));
                Cursor dataCursor = cr.query(ContactsContract.Data.CONTENT_URI,
                        new String[]{ContactsContract.Data.MIMETYPE,
                                ContactsContract.Data.DATA1,
                                ContactsContract.Data.DATA2,
                                ContactsContract.Data.DATA15},
                        ContactsContract.Data.RAW_CONTACT_ID + "=" + contactId,
                        null, null);
                if (dataCursor != null) {
                    while (dataCursor.moveToNext()) {
                        String mimeType = dataCursor
                                .getString(dataCursor
                                        .getColumnIndex(ContactsContract.Data.MIMETYPE));
                        if (ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                                .equals(mimeType)) {
                            //电话号码
                            contact.setNumber(dataCursor
                                    .getString(dataCursor
                                            .getColumnIndex(ContactsContract.Data.DATA1)));
                        } else if (ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
                                .equals(mimeType)) {
                            //邮件
                            contact.setEmail(
                                    dataCursor
                                            .getString(dataCursor
                                                    .getColumnIndex(ContactsContract.Data.DATA1)));
                        } else if (ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
                                .equals(mimeType)) {
                            //名字
                            contact.setName(
                                    dataCursor
                                            .getString(dataCursor
                                                    .getColumnIndex(ContactsContract.Data.DATA1)));
                        } else if (ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE
                                .equals(mimeType)) {
                            //头像
                            contact.setPortrait(dataCursor
                                    .getBlob(dataCursor
                                            .getColumnIndex(ContactsContract.Data.DATA15)));
                        }
                    }
                }
                if (dataCursor != null) {
                    dataCursor.close();
                    dataCursor = null;
                }

                contactList.add(contact);
            }

        if (contactCursor != null) {
            contactCursor.close();
            contactCursor = null;
        }
        return contactList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_contacts;
    }
}
