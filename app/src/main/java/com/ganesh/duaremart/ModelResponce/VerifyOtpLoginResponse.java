package com.ganesh.duaremart.ModelResponce;

import com.google.gson.annotations.SerializedName;

public class VerifyOtpLoginResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private VerifyOtpLoginResponseData data;

    public VerifyOtpLoginResponse(String status, VerifyOtpLoginResponseData data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VerifyOtpLoginResponseData getData() {
        return data;
    }

    public void setData(VerifyOtpLoginResponseData data) {
        this.data = data;
    }
}
