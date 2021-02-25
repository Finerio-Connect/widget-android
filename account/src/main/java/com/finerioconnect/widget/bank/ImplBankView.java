package com.finerioconnect.widget.bank;

import java.util.List;

import com.finerioconnect.widget.remote.data.Bank;

public interface ImplBankView {
    void showBankList(List<Bank> bankList);
    void showErrorMessage(Throwable t);
}
