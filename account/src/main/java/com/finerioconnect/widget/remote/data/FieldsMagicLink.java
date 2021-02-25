package com.finerioconnect.widget.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FieldsMagicLink {

    @SerializedName("data")
    @Expose
    private List<Field> data = null;
    @SerializedName("nextCursor")
    @Expose
    private Object nextCursor;

    public List<Field> getData() {
        return data;
    }

    public void setData(List<Field> data) {
        this.data = data;
    }

    public Object getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(Object nextCursor) {
        this.nextCursor = nextCursor;
    }

}
