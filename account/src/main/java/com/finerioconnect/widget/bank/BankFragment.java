package com.finerioconnect.widget.bank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.fragment.ImplFragmentTransaction;
import com.finerioconnect.widget.remote.data.Bank;
import com.finerioconnect.widget.utils.AbstractFragment;
import com.finerioconnect.widget.utils.EventFragment;

import java.util.List;

public class BankFragment extends AbstractFragment implements ImplBankView, BanksClickListener {

    private BankPresenter presenter;
    private final ImplFragmentTransaction mImplFragmentTransaction;

    public BankFragment(ImplFragmentTransaction implFragmentTransaction) {
        mImplFragmentTransaction = implFragmentTransaction;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_banks, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new BankPresenter(this, getActivity());
        setup();
    }

    private void setup() {
        presenter.getBankList();
    }

    @Override
    public void getOnClickListener(Bank bank) {
        mImplFragmentTransaction.setFragmentTransaction(EventFragment.Credentials, bank);
    }

    @Override
    public void showBankList(List<Bank> bankList) {
        View view = getView();

        assert view != null;
        RecyclerView rvBanks = view.findViewById(R.id.rvBanks);
        ProgressBar progressBarBanks = view.findViewById(R.id.progressBarBanks);
        progressBarBanks.setVisibility(View.GONE);

        rvBanks.setLayoutManager(new GridLayoutManager(getContext(), 2));

        BankMagicLinkAdapter banksAdapter = new BankMagicLinkAdapter(bankList, getActivity(), this);
        rvBanks.setAdapter(banksAdapter);
    }

    @Override
    public void showErrorMessage(Throwable t) {
        Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected String setFragmentTag() {
        return this.getClass().getSimpleName();
    }

}