package com.ganesh.duaremart.ModelResponce;

import retrofit2.http.Field;

public class SignUpRequestData {
    private String name;
    private String phn;
    private String email;
    private String pass;
    private String address;
    private String pin;
    private String shop_name;
    private String state_id;
    private String aadhar_card_pan_card;
    private String gst_no;
    private String transport_details;
    private String player_id;

    public SignUpRequestData(String name, String phn, String email, String pass, String address, String pin, String shop_name, String state_id, String aadhar_card_pan_card, String gst_no, String transport_details, String player_id) {
        this.name = name;
        this.phn = phn;
        this.email = email;
        this.pass = pass;
        this.address = address;
        this.pin = pin;
        this.shop_name = shop_name;
        this.state_id = state_id;
        this.aadhar_card_pan_card = aadhar_card_pan_card;
        this.gst_no = gst_no;
        this.transport_details = transport_details;
        this.player_id = player_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getAadhar_card_pan_card() {
        return aadhar_card_pan_card;
    }

    public void setAadhar_card_pan_card(String aadhar_card_pan_card) {
        this.aadhar_card_pan_card = aadhar_card_pan_card;
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

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }
}
