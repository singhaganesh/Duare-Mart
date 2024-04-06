package com.ganesh.duaremart.ModelResponce;


import com.google.gson.annotations.SerializedName;

public class SignUpResponseData {
    @SerializedName("user_otp1")
    private String user_otp1;
    @SerializedName("user_id")
    private String user_id;

    public SignUpResponseData(String user_otp1, String user_id) {
        this.user_otp1 = user_otp1;
        this.user_id = user_id;
    }

    public String getUser_otp1() {
        return user_otp1;
    }

    public void setUser_otp1(String user_otp1) {
        this.user_otp1 = user_otp1;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
