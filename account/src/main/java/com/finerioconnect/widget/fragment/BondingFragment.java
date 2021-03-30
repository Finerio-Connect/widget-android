package com.finerioconnect.widget.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.utils.AbstractFragment;
import com.finerioconnect.widget.utils.EventFragment;

public class BondingFragment extends AbstractFragment implements View.OnClickListener {

    private final ImplFragmentTransaction mImplFragmentTransaction;

    public BondingFragment(ImplFragmentTransaction implFragmentTransaction) {
        mImplFragmentTransaction = implFragmentTransaction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bonding, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setup();
    }

    private void setup() {
        View view = getView();
        assert view != null;
        TextView titleBack = view.findViewById(R.id.titleBack);
        titleBack.setOnClickListener(this);
        Button btnBondingAccount = view.findViewById(R.id.btnBondingAccount);
        btnBondingAccount.setOnClickListener(this);
        Button btnBondingCancel = view.findViewById(R.id.btnBondingCancel);
        btnBondingCancel.setOnClickListener(this);
    }

    @Override
    protected String setFragmentTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.titleBack){
            mImplFragmentTransaction.setFragmentTransaction(EventFragment.Bank, null);
        }else if (id == R.id.btnBondingAccount){
            mImplFragmentTransaction.setFragmentTransaction(EventFragment.Bank, null);
        } else if (id == R.id.btnBondingCancel){
            mImplFragmentTransaction.setFragmentTransaction(EventFragment.Welcome, null);
        }
    }

}