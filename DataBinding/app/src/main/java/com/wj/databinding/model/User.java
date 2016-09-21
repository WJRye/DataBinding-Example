package com.wj.databinding.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author：王江 on 2016/9/12 14:49
 * Description:
 */
public class User implements Parcelable {
    private String name;
    private String password;
    private int age;
    private int sex;
    private String email;
    private String address;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
        dest.writeInt(age);
        dest.writeInt(sex);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeString(number);
    }

    public static Parcelable.Creator CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            User user = new User();
            user.setName(source.readString());
            user.setPassword(source.readString());
            user.setAge(source.readInt());
            user.setSex(source.readInt());
            user.setEmail(source.readString());
            user.setAddress(source.readString());
            user.setNumber(source.readString());
            return user;
        }

        @Override
        public User[] newArray(int size) {
            return null;
        }
    };
}
