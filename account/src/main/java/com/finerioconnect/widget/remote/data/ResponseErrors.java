package com.finerioconnect.widget.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResponseErrors {

    @SerializedName("status")
    @Expose
    public String error;
    @SerializedName("exception")
    @Expose
    public String exception;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("path")
    @Expose
    public String path;
    @SerializedName("errors")
    @Expose
    private List<Error> errors = null;

    public List<Error> getErrors() {
        return errors;
    }
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getException() {
        return exception;
    }
    public void setException(String exception) {
        this.exception = exception;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    @NotNull
    @Override
    public String toString() {
        return getToStringError();
    }

    String getToStringError(){
        String error = null;

        if (getErrors() != null){
            error = "Errors:\n " + errors;
        }
        if (message != null){
            error = message;
        }

        return error;
    }

}
