package com.huangzisheng.www.bean;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huangzisheng on 2017/7/18.
 */

public class MztBean implements Parcelable {
    private String imageUrl;
    private String id;

    public String getImageUrl() {
        if(imageUrl.contains("http:"))
        return imageUrl;
        else{
            return "http:"+imageUrl;
        }
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    public MztBean(String imageUrl, String id, String text) {
        this.imageUrl = imageUrl;
        this.id = id;
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeString(this.id);
        dest.writeString(this.text);
    }

    public MztBean() {
    }

    protected MztBean(Parcel in) {
        this.imageUrl = in.readString();
        this.id = in.readString();
        this.text = in.readString();
    }

    public static final Creator<MztBean> CREATOR = new Creator<MztBean>() {
        @Override
        public MztBean createFromParcel(Parcel source) {
            return new MztBean(source);
        }

        @Override
        public MztBean[] newArray(int size) {
            return new MztBean[size];
        }
    };
}
