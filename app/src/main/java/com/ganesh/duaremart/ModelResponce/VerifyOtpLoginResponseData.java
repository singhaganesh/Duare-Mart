package com.ganesh.duaremart.ModelResponce;

import com.google.gson.annotations.SerializedName;

public class VerifyOtpLoginResponseData {

    @SerializedName("name")
    private String name;
    @SerializedName("u_id")
    private String u_id;
    @SerializedName("address")
    private String address;
    @SerializedName("phone")
    private String phone;
    @SerializedName("picture")
    private String picture;
    @SerializedName("pincode")
    private String pincode;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("user_type")
    private String user_type;

    @SerializedName("shop_name")
    private String shop_name;

    @SerializedName("gst_no")
    private String gst_no;

    @SerializedName("transport_details")
    private String transport_details;

    public VerifyOtpLoginResponseData(String name, String u_id, String address, String phone, String picture, String pincode, String email, String password, String user_type, String shop_name, String gst_no, String transport_details) {
        this.name = name;
        this.u_id = u_id;
        this.address = address;
        this.phone = phone;
        this.picture = picture;
        this.pincode = pincode;
        this.email = email;
        this.password = password;
        this.user_type = user_type;
        this.shop_name = shop_name;
        this.gst_no = gst_no;
        this.transport_details = transport_details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getGst_no() {
        return gst_no;
    }

    public void setGst_no(String gst_no) {
        this.gst_no = gst_no;
    }

    public String getTransport_details() {
        return transport_details;
    }

    public void setTransport_details(String transport_details) {
        this.transport_details = transport_details;
    }
}
