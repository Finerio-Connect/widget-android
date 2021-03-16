package com.finerioconnect.widget.model;

public class Singleton {

    private static Singleton instance = null;
    private static AccountWidget mAccountWidget;

    public synchronized static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void setAccountWidget(AccountWidget accountWidget) {
        mAccountWidget = accountWidget;
    }

    public AccountWidget getDataWidget(){
        return mAccountWidget;
    }

}