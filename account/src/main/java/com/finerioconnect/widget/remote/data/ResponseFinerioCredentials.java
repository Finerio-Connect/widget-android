package com.finerioconnect.widget.remote.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResponseFinerioCredentials {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("automaticFetching")
    @Expose
    private Boolean automaticFetching;
    //@SerializedName("dateCreated")
    //@Expose
    //private Long dateCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getAutomaticFetching() {
        return automaticFetching;
    }

    public void setAutomaticFetching(Boolean automaticFetching) {
        this.automaticFetching = automaticFetching;
    }

    /*public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }*/
    
    @SerializedName("errors")
    @Expose
    private List<Error> errors = null;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @NotNull
    @Override
    public String toString() {
        return "Errors:\n " + errors;
    }

}
