package com.pgcn.udcmakeabaking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import paperparcel.PaperParcel;

@PaperParcel
public class Baking implements Parcelable {

    public static final String KEY_BAKING = "KEY_BAKING";
    public static final String KEY_BAKING_NAME = "KEY_BAKING_NAME";
    public static final String KEY_BAKING_IMAGE = "KEY_BAKING_IMAGE";

    public static final String PREFERENCE_NAME = "BAKING_APP";
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients;

    @SerializedName("steps")
    @Expose
    private List<Step> steps;
    @SerializedName("servings")
    @Expose
    private Integer servings;
    @SerializedName("image")
    @Expose
    private String image;

    private Baking(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.ingredients = new ArrayList<Ingredient>();
        this.steps = new ArrayList<Step>();
        in.readList(this.ingredients, (com.pgcn.udcmakeabaking.model.Ingredient.class.getClassLoader()));
        in.readList(this.steps, (com.pgcn.udcmakeabaking.model.Step.class.getClassLoader()));
        this.servings = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Baking() {
    }

    public static final Creator<Baking> CREATOR = new Creator<Baking>() {
        @Override
        public Baking createFromParcel(Parcel in) {
            return new Baking(in);
        }

        @Override
        public Baking[] newArray(int size) {
            return new Baking[size];
        }
    };

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("ingredients", ingredients).append("steps", steps).append("servings", servings).append("image", image).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(ingredients);
        dest.writeList(steps);
        dest.writeValue(servings);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

}