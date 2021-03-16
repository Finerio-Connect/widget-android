package com.finerioconnect.widget.credentials;

import java.util.List;

import com.finerioconnect.widget.remote.data.Field;
import com.finerioconnect.widget.remote.data.ResponseFinerioCredentials;

public interface ImplCredentialsView {
    void showErrorMessage(Throwable t);
    void showFields(List<Field> data);
    void showFinerioCredentials(ResponseFinerioCredentials responseFinerioCredentials);
}
