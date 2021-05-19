package com.finerioconnect.widget.utils;

import com.finerioconnect.widget.remote.data.Field;

import java.util.List;

public class SessionFieldList {
    private final List<Field> fieldList;
    public List<Field> getFieldList() { return fieldList; }

    private final int bankId;
    public int getBankId() { return bankId; }

    public SessionFieldList(int bankId, List<Field> fieldList) {
        this.fieldList = fieldList;
        this.bankId = bankId;
    }

}
