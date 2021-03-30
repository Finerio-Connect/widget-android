package com.finerioconnect.widget.synchronizing;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.remote.data.Account;

class AccountViewHolder extends RecyclerView.ViewHolder {

  private Account accountRow;
  private final TextView tv_number_account;
  private final LottieAnimationView mLoadingLottieLoad;
  private final LottieAnimationView mLoadingLottieReady;

  public AccountViewHolder(@NonNull View itemView) {
    super(itemView);
    tv_number_account = itemView.findViewById(R.id.tv_number_account);
    mLoadingLottieLoad = itemView.findViewById(R.id.mLoadingLottieLoad);
    mLoadingLottieReady = itemView.findViewById(R.id.mLoadingLottieReady);
  }

  public void setAccountRow( Account accountRow ) {
    this.accountRow = accountRow;
    setViewData();
  }

  private void setViewData() {
    tv_number_account.setText(accountRow.getName());
    String status = accountRow.getStatus();

    switch (status){
      case "TRANSACTIONS_CREATED" :
        mLoadingLottieLoad.setVisibility(View.GONE);
        mLoadingLottieReady.setVisibility(View.VISIBLE);
        break;
      case "ACCOUNT_CREATED" :
        mLoadingLottieLoad.setVisibility(View.VISIBLE);
        mLoadingLottieReady.setVisibility(View.GONE);
        break;
      default:
        break;
    }
/*
    if (accountRow.getStatus().equals("TRANSACTIONS_CREATED")){
      //mLoadingLottie.cancelAnimation();
      mLoadingLottieLoad.setAnimationFromUrl("https://cdn.finerio.mx/widget/account_ready");
      mLoadingLottie.playAnimation();
      mLoadingLottie.loop(false);
    }else if (accountRow.getStatus().equals("ACCOUNT_CREATED")){
      mLoadingLottieLoad.setAnimationFromUrl("https://cdn.finerio.mx/widget/account_loading");
      mLoadingLottie.playAnimation();
      mLoadingLottie.loop(true);
    }*/

  }

}
