package com.finerioconnect.widget.bank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.remote.data.Bank;

public class BankMagicLinkAdapter extends RecyclerView.Adapter<BankMagicLinkViewHolder> {

    private final List<Bank> mBankList;
    private final Activity mContext;
    private final BanksClickListener mBanksClickListener;

    public BankMagicLinkAdapter (List<Bank> bankList, Activity context, BanksClickListener banksClickListener){
        this.mBankList = bankList;
        this.mContext = context;
        this.mBanksClickListener = banksClickListener;
    }

    @NonNull
    @Override
    public BankMagicLinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.bank_card, parent, false );
        return new BankMagicLinkViewHolder(view, mContext, mBanksClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BankMagicLinkViewHolder viewHolder, int position) {
        Bank bank = mBankList.get( position );
        viewHolder.setBankRow( bank );
    }

    @Override
    public int getItemCount() { return mBankList.size(); }

}

