package com.finerioconnect.widget.credentials;

import com.finerioconnect.widget.remote.data.Field;
import com.finerioconnect.widget.remote.data.ResponseFinerioCredentials;

import java.util.List;

public interface ImplCredentialsView {
    void showErrorMessage(Throwable t);
    void showFields(List<Field> data);
    void showFinerioCredentials(ResponseFinerioCredentials responseFinerioCredentials);
}
