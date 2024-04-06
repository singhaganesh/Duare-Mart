package com.ganesh.duaremart.ModelResponce;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private SignUpResponseData data;

    public SignUpResponse(String status, SignUpResponseData data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SignUpResponseData getData() {
        return data;
    }

    public void setData(SignUpResponseData data) {
        this.data = data;
    }
}
