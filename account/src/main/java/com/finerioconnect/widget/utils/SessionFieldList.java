package com.finerioconnect.widget.utils;

import com.finerioconnect.widget.remote.data.Field;

import java.util.List;

public class SessionFieldList {
    private List<Field> fieldList;
    public List<Field> getFieldList() { return fieldList; }
    public void setFieldList(List<Field> fieldList) { this.fieldList = fieldList; }

    private int bankId;
    public int getBankId() { return bankId; }
    public void setBankId(int bankId) { this.bankId = bankId; }

    public SessionFieldList(int bankId, List<Field> fieldList) {
        this.fieldList = fieldList;
        this.bankId = bankId;
    }

}
