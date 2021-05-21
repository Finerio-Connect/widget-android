package com.finerioconnect.widget.bank;

import com.finerioconnect.widget.remote.data.Bank;

import java.util.List;

public interface ImplBankView {
    void showBankList(List<Bank> bankList);
    void showErrorMessage(Throwable t);
}
