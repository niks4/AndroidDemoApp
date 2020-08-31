package com.project.machinetest.model;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Body;
import retrofit2.http.Field;

public class Credential {

    @SerializedName("userName")
    String userName;
    @SerializedName("password")
    String password;

    public Credential(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
