package com.finerioconnect.widget.remote.data;

public class ItemDataBase {

    private String code="";
    private String message="";
    private String status="";
    private String bankToken="";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankToken() {
        return bankToken;
    }

    public void setBankToken(String bankToken) {
        this.bankToken = bankToken;
    }

    @Override
    public String toString() {
        return "ItemDataBase{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", bankToken='" + bankToken + '\'' +
                '}';
    }

}
