package com.example.eventsadminapp;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @FormUrlEncoded
    @POST("attendance")
    Call<Response> userAttendance(@Field("regno") String regno);

    @POST("food")
    Call<Response>foodDistribution(@Field("regno")String regno);

}
