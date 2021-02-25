package com.finerioconnect.widget.remote.data;

public class Account {

    String status = "";
    String name = "";
    String id = "";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account(String id, String status, String name) {
        this.id = id;
        this.status = status;
        this.name = name;
    }

    public Account() { }

    @Override
    public String toString() {
        return "Account{" +
                "status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
