package com.finerioconnect.widget.bank;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.remote.ApiClient;
import com.finerioconnect.widget.remote.ApiService;
import com.finerioconnect.widget.remote.ServiceUrl;
import com.finerioconnect.widget.remote.data.Bank;
import com.finerioconnect.widget.remote.data.BanksMagicLink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankPresenter implements ImplBankPresenter {

    private final ApiService mApiService;
    private final Context mContext;
    private final ImplBankView mImplBankView;

    public BankPresenter (ImplBankView implBankView, Context context) {
        this.mApiService = ApiClient.getClient(ServiceUrl.MAGIC_LINK).create(ApiService.class);
        this.mImplBankView = implBankView;
        this.mContext = context;
    }

    @Override
    public void getBankList(){
        mApiService.doGetBanks().enqueue(new Callback<BanksMagicLink>() {
            @Override
            public void onResponse(@NotNull Call<BanksMagicLink> call, @NotNull Response<BanksMagicLink> response) {
                if (response.body() != null){
                    List<Bank> bankList = new ArrayList<>();

                    for (Bank bank: response.body().getData()) {
                        if (bank.getStatus().equals("ACTIVE") || bank.getStatus().equals("PARTIALLY_ACTIVE")){
                            bankList.add(bank);
                        }
                    }

                    mImplBankView.showBankList(bankList);
                }else{
                    onFailure(call, new Throwable(mContext.getString(R.string.text_error_server)));
                }
            }

            @Override
            public void onFailure(@NotNull Call<BanksMagicLink> call, @NotNull Throwable t) {
                call.cancel();
                mImplBankView.showErrorMessage(t);
            }
        });
    }

}
