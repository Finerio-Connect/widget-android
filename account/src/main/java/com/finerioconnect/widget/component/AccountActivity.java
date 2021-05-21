package com.finerioconnect.widget.component;

import android.os.Bundle;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.bank.BankFragment;
import com.finerioconnect.widget.credentials.CredentialsFragment;
import com.finerioconnect.widget.fragment.BondingFragment;
import com.finerioconnect.widget.fragment.ErrorFragment;
import com.finerioconnect.widget.fragment.ImplFragmentTransaction;
import com.finerioconnect.widget.model.AccountWidget;
import com.finerioconnect.widget.model.Singleton;
import com.finerioconnect.widget.remote.data.Bank;
import com.finerioconnect.widget.synchronizing.SynchronizingFragment;
import com.finerioconnect.widget.utils.AbstractFragment;
import com.finerioconnect.widget.utils.BaseActivityFragment;
import com.finerioconnect.widget.utils.EventFragment;
import com.finerioconnect.widget.utils.FragmentControllerDelegate;
import com.google.firebase.FirebaseApp;

import java.util.Objects;

public class AccountActivity extends BaseActivityFragment implements ImplFragmentTransaction {

    private BankFragment mBankFragment;
    private CredentialsFragment mCredentialsFragment;

    private BondingFragment mBondingFragment;
    private ErrorFragment mErrorFragment;
    private SynchronizingFragment mSynchronizingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setTitle("Bancos");
        FirebaseApp.initializeApp(this);
        AccountWidget accountWidget = (AccountWidget) getIntent().getSerializableExtra("AccountWidget");
        Singleton.getInstance().setAccountWidget(accountWidget);
        loadFragment(mBankFragment, FragmentControllerDelegate.TRANSITION.FADE);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.accountFinerio;
    }

    @Override
    protected void initPersistFragments() {
        mBankFragment = new BankFragment(this);
        mBondingFragment = new BondingFragment(this);
    }

    @Override
    public void onBackPressed() {
        AbstractFragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof BankFragment){
            super.onBackPressed();
        }else if (currentFragment instanceof CredentialsFragment){
            setTitle("Bancos");
            loadFragment(mBankFragment, FragmentControllerDelegate.TRANSITION.BACK);
        }else if (currentFragment instanceof BondingFragment){
            setTitle("Bancos");
            loadFragment(mBankFragment, FragmentControllerDelegate.TRANSITION.BACK);
        }else if (currentFragment instanceof ErrorFragment){
            setTitle("Bancos");
            loadFragment(mBankFragment, FragmentControllerDelegate.TRANSITION.BACK);
        }else if (currentFragment instanceof SynchronizingFragment){
            setTitle("Bancos");
            loadFragment(mBankFragment, FragmentControllerDelegate.TRANSITION.BACK);
        }else{ super.onBackPressed(); }
    }

    @Override
    public void setFragmentTransaction(EventFragment eventFragment, Object object) {

        if (object instanceof Bank){
            mCredentialsFragment = new CredentialsFragment(this, (Bank) object);
        } else if (object instanceof Throwable){
            mErrorFragment = new ErrorFragment(this, (Throwable) object);
        } else if (object instanceof String){
            mSynchronizingFragment = new SynchronizingFragment(this, (String) object);
        }

        switch (eventFragment){
            case Bank:
                loadFragment(mBankFragment);
                setTitle("Bancos");
                break;
            case Credentials:
                loadFragment(mCredentialsFragment);
                setTitle("Credencial ");
                break;
            case Bonding:
                loadFragment(mBondingFragment);
                setTitle("Mensaje de Éxito");
                break;
            case Error:
                loadFragment(mErrorFragment);
                setTitle("Mensage de Error");
                break;
            case Synchronizing:
                loadFragment(mSynchronizingFragment);
                setTitle("Sincronización");
                break;
        }
    }

}