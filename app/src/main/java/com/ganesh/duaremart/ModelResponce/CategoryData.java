package com.ganesh.duaremart.ModelResponce;

import com.google.gson.annotations.SerializedName;

public class CategoryData {

    @SerializedName("id")
    private String id;
    @SerializedName("subcategory")
    private String subcategory;
    @SerializedName("name")
    private String name;
    @SerializedName("category_image")
    private String category_image;

    public CategoryData(String id, String subcategory, String name, String category_image) {
        this.id = id;
        this.subcategory = subcategory;
        this.name = name;
        this.category_image = category_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }
}
