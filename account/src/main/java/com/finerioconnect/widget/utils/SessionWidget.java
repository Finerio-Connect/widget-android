package com.finerioconnect.widget.utils;

import com.finerioconnect.widget.remote.data.Bank;
import com.finerioconnect.widget.remote.data.Field;

import java.util.ArrayList;
import java.util.List;

public class SessionWidget {

    public static List<Bank> bankList = new ArrayList<>();
    public static List<Bank> getBankList() { return bankList; }
    public static void setBankList(List<Bank> bankList) { SessionWidget.bankList = bankList; }

    public static List<SessionFieldList> sessionFieldLists = new ArrayList<>();
    public static List<SessionFieldList> getSessionFieldLists() { return sessionFieldLists; }
    public static void setSessionFieldLists(List<SessionFieldList> sessionFieldLists) { SessionWidget.sessionFieldLists = sessionFieldLists; }
    public static void addNewSessionFieldLists( SessionFieldList sessionFieldList){
        List<SessionFieldList> sessionFieldLists = getSessionFieldLists();
        sessionFieldLists.add( sessionFieldList );
        setSessionFieldLists(sessionFieldLists);
    }

}