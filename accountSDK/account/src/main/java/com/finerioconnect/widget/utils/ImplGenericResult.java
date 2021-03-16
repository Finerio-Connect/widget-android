package com.finerioconnect.widget.utils;

public interface ImplGenericResult {
    void onSuccess(String message);
    void onFailure(Throwable t);
}
