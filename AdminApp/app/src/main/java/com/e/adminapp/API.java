package com.e.adminapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("attendence")
    Call<ResponseBody> userAttendence(
            @Field("regno") String regno
    );

    @FormUrlEncoded
    @POST("food")
    Call<ResponseBody> userFood(
            @Field("regno") String regno
    );

}
