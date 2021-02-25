package com.finerioconnect.widget.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.finerioconnect.widget.remote.RemoteConstants;

public class Bank {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrlImageOn() {
        return RemoteConstants.IMAGE_BANK_ON.replace("{bankId}", String.valueOf(id));
    }

    public String getUrlImageOff() {
        return RemoteConstants.IMAGE_BANK_OFF.replace("{bankId}", String.valueOf(id));
    }

}
