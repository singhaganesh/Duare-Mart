package com.ganesh.duaremart.ModelResponce;

import com.google.gson.annotations.SerializedName;

public class VerifyOtpSignupResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private String data;

    public VerifyOtpSignupResponse(String status, String data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
