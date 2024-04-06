package com.ganesh.duaremart.ModelResponce;

public class VerifyOptSignupRequestData {

    private String user_id;
    private String otp;

    public VerifyOptSignupRequestData(String user_id, String top) {
        this.user_id = user_id;
        this.otp = top;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTop() {
        return otp;
    }

    public void setTop(String top) {
        this.otp = top;
    }
}
