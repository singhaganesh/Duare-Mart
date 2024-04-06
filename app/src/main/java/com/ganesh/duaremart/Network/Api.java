package com.ganesh.duaremart.Network;

import com.ganesh.duaremart.ModelResponce.CategoryProductsResponse;
import com.ganesh.duaremart.ModelResponce.CategoryResponse;
import com.ganesh.duaremart.ModelResponce.LogInResponse;
import com.ganesh.duaremart.ModelResponce.SignUpResponse;
import com.ganesh.duaremart.ModelResponce.VerifyOptSignupRequestData;
import com.ganesh.duaremart.ModelResponce.VerifyOtpLoginResponse;
import com.ganesh.duaremart.ModelResponce.VerifyOtpSignupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    @POST("signup")
    @FormUrlEncoded
    @Headers("Keydata: 874121458454233")
    Call<SignUpResponse> signup(
                @Field("name") String name,
                @Field("phn") String phn,
                @Field("email") String email,
                @Field("pass") String pass,
                @Field("address") String address,
                @Field("pin") String pin,
                @Field("shop_name") String shop_name,
                @Field("state_id") String state_id,
                @Field("aadhar_card_pan_card") String aadhar_card_pan_card,
                @Field("gst_no") String gst_no,
                @Field("transport_details") String transport_details,
                @Field("player_id") String player_id
            );


    @POST("verify_otp_signup")
    @FormUrlEncoded
    @Headers("Keydata: 874121458454233")
    Call<VerifyOtpSignupResponse> verify_otp_signup(
            @Field("user_id") String user_id,
            @Field("otp") String otp
            );

    @POST("login")
    @FormUrlEncoded
    @Headers("Keydata: 874121458454233")
    Call<LogInResponse> login(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("player_id") String player_id
    );

    @POST("verify_otp_login")
    @FormUrlEncoded
    @Headers("Keydata: 874121458454233")
    Call<VerifyOtpLoginResponse> verify_otp_login(
            @Field("user_id") String user_id,
            @Field("otp") String otp
    );

    @GET("maincategory")
    @Headers("Keydata: 874121458454233")
    Call<CategoryResponse> getAllCategoryDetails();

    @POST("maincategory_products")
    @FormUrlEncoded
    @Headers("Keydata: 874121458454233")
    Call<CategoryProductsResponse> getAllCategoryProductDetails(
            @Field("category_id") String category_id
    );


}
