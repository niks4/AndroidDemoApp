package com.project.machinetest.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("AuthenticateResult")
    String AuthenticateResult;

    public String getAuthenticateResult() {
        return AuthenticateResult;
    }

    public void setAuthenticateResult(String authenticateResult) {
        AuthenticateResult = authenticateResult;
    }
}
