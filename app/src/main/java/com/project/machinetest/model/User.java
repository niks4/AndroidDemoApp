package com.project.machinetest.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("fullaccountnumber")
    String fullaccountnumber;
    @SerializedName("headname")
    String headname;
    @SerializedName("customername")
    String customername;

    public User(String fullaccountnumber, String headname, String customername) {
        this.fullaccountnumber = fullaccountnumber;
        this.headname = headname;
        this.customername = customername;
    }

    public String getFullaccountnumber() {
        return fullaccountnumber;
    }

    public void setFullaccountnumber(String fullaccountnumber) {
        this.fullaccountnumber = fullaccountnumber;
    }

    public String getHeadname() {
        return headname;
    }

    public void setHeadname(String headname) {
        this.headname = headname;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

}
