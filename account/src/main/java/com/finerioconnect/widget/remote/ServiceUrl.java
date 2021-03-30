package com.finerioconnect.widget.remote;

public enum ServiceUrl {

    MAGIC_LINK (RemoteConstants.API_ML),
    API_FINERIO (RemoteConstants.API_F);

    private final String mUrl;

    ServiceUrl(String url) {
        this.mUrl = url;
    }

    @Override
    public String toString() {
        return mUrl;
    }

}
