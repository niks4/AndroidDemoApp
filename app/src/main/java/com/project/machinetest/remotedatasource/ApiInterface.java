package com.project.machinetest.remotedatasource;

import com.project.machinetest.model.AccountDetails;
import com.project.machinetest.model.Credential;
import com.project.machinetest.model.Result;
import com.project.machinetest.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("Authenticate")
    @Headers("Accept: application/json")
    Call<Result> getLogin(@Header("Authorization") String authorization, @Body Credential credential);

    @GET("GetHdAccountsAndMenus")
    Call<AccountDetails> getUsers(@Query("LoginId") String userId);

}
