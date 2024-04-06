package com.ganesh.duaremart.ModelResponce;

import com.google.gson.annotations.SerializedName;

public class CategoryProductsResponseData {

    @SerializedName("product_qty")
    private String product_qty;
    @SerializedName("total_tax")
    private String total_tax;
    @SerializedName("price_tax")
    private String price_tax;
    @SerializedName("product_id")
    private String product_id;
    @SerializedName("out_of_stock")
    private String out_of_stock;
    @SerializedName("stock")
    private String stock;
    @SerializedName("product_name")
    private String product_name;
    @SerializedName("product_title")
    private String product_title;
    @SerializedName("image1")
    private String image1;
    @SerializedName("image2")
    private String image2;
    @SerializedName("varient")
    private String varient;
    @SerializedName("unit")
    private String unit;
    @SerializedName("strike_price")
    private String strike_price;
    @SerializedName("price")
    private String price;

    public CategoryProductsResponseData(String product_qty, String total_tax, String price_tax, String product_id, String out_of_stock, String stock, String product_name, String product_title, String image1, String image2, String varient, String unit, String strike_price, String price) {
        this.product_qty = product_qty;
        this.total_tax = total_tax;
        this.price_tax = price_tax;
        this.product_id = product_id;
        this.out_of_stock = out_of_stock;
        this.stock = stock;
        this.product_name = product_name;
        this.product_title = product_title;
        this.image1 = image1;
        this.image2 = image2;
        this.varient = varient;
        this.unit = unit;
        this.strike_price = strike_price;
        this.price = price;
    }

    public String getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(String product_qty) {
        this.product_qty = product_qty;
    }

    public String getTotal_tax() {
        return total_tax;
    }

    public void setTotal_tax(String total_tax) {
        this.total_tax = total_tax;
    }

    public String getPrice_tax() {
        return price_tax;
    }

    public void setPrice_tax(String price_tax) {
        this.price_tax = price_tax;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getOut_of_stock() {
        return out_of_stock;
    }

    public void setOut_of_stock(String out_of_stock) {
        this.out_of_stock = out_of_stock;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getVarient() {
        return varient;
    }

    public void setVarient(String varient) {
        this.varient = varient;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStrike_price() {
        return strike_price;
    }

    public void setStrike_price(String strike_price) {
        this.strike_price = strike_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
