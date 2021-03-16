package com.finerioconnect.widget.credentials;

public interface ImplCredentialsPresenter {
    void setCredentials(int bankId, String userName, String password, String securityCode);
    void getFieldByBankId(Integer bankId);
}