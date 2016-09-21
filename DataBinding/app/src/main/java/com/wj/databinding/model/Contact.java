package com.wj.databinding.model;

import android.databinding.BindingAdapter;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * Author：王江 on 2016/9/14 15:26
 * Description:
 */
public class Contact {
    private String number;
    private String email;
    private String name;
    private byte[] portrait;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPortrait() {
        return portrait;
    }

    public void setPortrait(byte[] portrait) {
        this.portrait = portrait;
    }

    @BindingAdapter("data")
    public static void setImageBitmap(ImageView imageView, byte[] data) {
        if (data != null)
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
    }
}
