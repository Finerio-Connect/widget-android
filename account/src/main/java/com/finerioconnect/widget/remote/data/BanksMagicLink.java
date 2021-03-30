package com.finerioconnect.widget.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BanksMagicLink {

    @SerializedName("data")
    @Expose
    private List<Bank> data = null;
/*    @SerializedName("nextCursor")
    @Expose
    private Object nextCursor;*/

    public List<Bank> getData() {
        return data;
    }

    public void setData(List<Bank> data) {
        this.data = data;
    }

/*
    public Object getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(Object nextCursor) {
        this.nextCursor = nextCursor;
    }
*/

}
