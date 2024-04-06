package com.ganesh.duaremart.ModelResponce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryProductsResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private List<CategoryProductsResponseData> data;

    public CategoryProductsResponse(String status, List<CategoryProductsResponseData> data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CategoryProductsResponseData> getData() {
        return data;
    }

    public void setData(List<CategoryProductsResponseData> data) {
        this.data = data;
    }
}
