package com.pgcn.udcmakeabaking.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;


public class Baking implements Parcelable {

    public static final String KEY_BAKING = "KEY_BAKING";
    public static final String KEY_BAKING_NAME = "KEY_BAKING_NAME";
    public static final String KEY_BAKING_IMAGE = "KEY_BAKING_IMAGE";
    public final static Parcelable.Creator<Baking> CREATOR = new Creator<Baking>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Baking createFromParcel(Parcel in) {
            return new Baking(in);
        }

        public Baking[] newArray(int size) {
            return (new Baking[size]);
        }

    };
    public static final String PREFERENCE_NAME = "BAKING_APP";
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private ArrayList<Ingredient> ingredients;

    @SerializedName("steps")
    @Expose
    private ArrayList<Step> steps;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Baking withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Baking withName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Baking withIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public Baking withSteps(ArrayList<Step> steps) {
        this.steps = steps;
        return this;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public Baking withServings(Integer servings) {
        this.servings = servings;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Baking withImage(String image) {
        this.image = image;
        return this;
    }

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