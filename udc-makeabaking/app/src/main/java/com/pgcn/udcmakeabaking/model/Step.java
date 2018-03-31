package com.pgcn.udcmakeabaking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import paperparcel.PaperParcel;

@PaperParcel
public class Step implements Parcelable {

    public static final String KEY_STEP = "KEY_STEP";
    public static final String KEY_STEP_LIST = "KEY_STEP_LIST";
    public static final String KEY_STEP_LIST_SIZE = "KEY_STEP_LIST_SIZE";
    public static final String KEY_STEP_POSITION = "KEY_STEP_POSITION";

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("videoURL")
    @Expose
    private String videoURL;
    @SerializedName("thumbnailURL")
    @Expose
    private String thumbnailURL;

    protected Step(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.shortDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.videoURL = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnailURL = ((String) in.readValue((String.class.getClassLoader())));
    }


    public Step() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id)
                .append("shortDescription", shortDescription)
                .append("description", description)
                .append("videoURL", videoURL)
                .append("thumbnailURL", thumbnailURL).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(shortDescription);
        dest.writeValue(description);
        dest.writeValue(videoURL);
        dest.writeValue(thumbnailURL);
    }

    public int describeContents() {
        return 0;
    }


}