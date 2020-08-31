package com.project.machinetest.model;

import com.google.gson.annotations.SerializedName;

public class AccountDetails {

    @SerializedName("GetHdAccountsAndMenusResult")
    String GetHdAccountsAndMenusResult;

    public String getGetHdAccountsAndMenusResult() {
        return GetHdAccountsAndMenusResult;
    }

    public void setGetHdAccountsAndMenusResult(String getHdAccountsAndMenusResult) {
        GetHdAccountsAndMenusResult = getHdAccountsAndMenusResult;
    }

}
