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

public class ErrorFragment extends AbstractFragment implements View.OnClickListener {

    private final ImplFragmentTransaction mImplFragmentTransaction;
    private final String mMessage;

    public ErrorFragment(ImplFragmentTransaction implFragmentTransaction, Throwable message) {
        this.mImplFragmentTransaction = implFragmentTransaction;
        this.mMessage = message.getMessage();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View itemView = getView();

        assert itemView != null;
        Button btnTryAgain = itemView.findViewById(R.id.btnTryAgain);
        btnTryAgain.setOnClickListener(this);
        Button btnErrorCancel = itemView.findViewById(R.id.btnErrorCancel);
        btnErrorCancel.setOnClickListener(this);
        TextView titleBack = itemView.findViewById(R.id.titleBack);
        titleBack.setOnClickListener(this);
        TextView tvMessageError = itemView.findViewById(R.id.tvMessageError);
        tvMessageError.setText(mMessage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_error, container, false);
    }

    @Override
    protected String setFragmentTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnTryAgain) {
            mImplFragmentTransaction.setFragmentTransaction(EventFragment.Bank, null);
        } else if (id == R.id.btnErrorCancel) {
            mImplFragmentTransaction.setFragmentTransaction(EventFragment.Bank, null);
        } else if (id == R.id.titleBack) {
            mImplFragmentTransaction.setFragmentTransaction(EventFragment.Bank, null);
        }
    }

}