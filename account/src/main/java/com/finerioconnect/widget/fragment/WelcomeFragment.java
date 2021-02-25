package com.finerioconnect.widget.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.model.Singleton;
import com.finerioconnect.widget.utils.AbstractFragment;
import com.finerioconnect.widget.utils.EventFragment;

public class WelcomeFragment extends AbstractFragment implements View.OnClickListener {

    private final ImplFragmentTransaction mImplFragmentTransaction;

    public static WelcomeFragment newInstance(ImplFragmentTransaction implFragmentTransaction) {
        Bundle args = new Bundle();
        WelcomeFragment fragment = new WelcomeFragment(implFragmentTransaction);
        fragment.setArguments(args);
        return fragment;
    }

    public WelcomeFragment(ImplFragmentTransaction implFragmentTransaction) {
        mImplFragmentTransaction = implFragmentTransaction;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View itemView = getView();

        assert itemView != null;
        Button btnContinue = itemView.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(this);

        TextView tvGreetingUser = itemView.findViewById(R.id.tvGreetingUser);
        tvGreetingUser.setText(getString(R.string.text_tv_h2,
                Singleton.getInstance().getDataWidget().getCustomerName()
        ));

        TextView tvSubTitle = itemView.findViewById(R.id.tvSubTitle);
        tvSubTitle.setText(getString(R.string.text_tv_title_customer,
                Singleton.getInstance().getDataWidget().getCompanyName()
        ));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onClick(View view) {
        mImplFragmentTransaction.setFragmentTransaction(EventFragment.Bank, null);
    }

    @Override
    protected String setFragmentTag() {
        return this.getClass().getSimpleName();
    }

}