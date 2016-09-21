package com.wj.databinding.model;

/**
 * Author：王江 on 2016/9/13 10:57
 * Description:
 */
public class CallLog {

    private String cacheName;
    private String number;
    private String geocodedLocation;
    private long date;
    private int type;

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getGeocodedLocation() {
        return geocodedLocation;
    }

    public void setGeocodedLocation(String geocodedLocation) {
        this.geocodedLocation = geocodedLocation;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
