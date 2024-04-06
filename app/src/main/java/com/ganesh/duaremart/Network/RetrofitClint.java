package com.ganesh.duaremart.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClint {
    private static final String BASE_URL = " https://jbmatrix.in/dev33/duaremart/api/";
    private static RetrofitClint retrofitClint;
    private static Retrofit retrofit;

    private RetrofitClint(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClint getInstance(){
        if (retrofitClint == null){
            retrofitClint = new RetrofitClint();
        }
        return retrofitClint;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
