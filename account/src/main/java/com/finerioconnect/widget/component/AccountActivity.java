package com.finerioconnect.widget.component;

import android.os.Bundle;

import com.google.firebase.FirebaseApp;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.bank.BankFragment;
import com.finerioconnect.widget.credentials.CredentialsFragment;
import com.finerioconnect.widget.fragment.BondingFragment;
import com.finerioconnect.widget.fragment.ErrorFragment;
import com.finerioconnect.widget.fragment.ImplFragmentTransaction;
import com.finerioconnect.widget.fragment.WelcomeFragment;
import com.finerioconnect.widget.model.AccountWidget;
import com.finerioconnect.widget.model.Singleton;
import com.finerioconnect.widget.remote.data.Bank;
import com.finerioconnect.widget.synchronizing.SynchronizingFragment;
import com.finerioconnect.widget.utils.AbstractFragment;
import com.finerioconnect.widget.utils.BaseActivityFragment;
import com.finerioconnect.widget.utils.EventFragment;
import com.finerioconnect.widget.utils.FragmentControllerDelegate;

public class AccountActivity extends BaseActivityFragment implements ImplFragmentTransaction {

    private WelcomeFragment mWelcomeFragment;
    private BankFragment mBankFragment;
    private CredentialsFragment mCredentialsFragment;

    private BondingFragment mBondingFragment;
    private ErrorFragment mErrorFragment;
    private SynchronizingFragment mSynchronizingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        FirebaseApp.initializeApp(this);
        AccountWidget accountWidget = (AccountWidget) getIntent().getSerializableExtra("AccountWidget");
        Singleton.getInstance().setAccountWidget(accountWidget);
        loadFragment(mWelcomeFragment, FragmentControllerDelegate.TRANSITION.FADE);
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.accountFinerio;
    }

    @Override
    protected void initPersistFragments() {
        mWelcomeFragment = WelcomeFragment.newInstance(this);
        mBankFragment = new BankFragment(this);
        mBondingFragment = new BondingFragment(this);
    }

    @Override
    public void onBackPressed() {
        AbstractFragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof WelcomeFragment){
            super.onBackPressed();
        }else if (currentFragment instanceof BankFragment){
            loadFragment(mWelcomeFragment, FragmentControllerDelegate.TRANSITION.BACK);
        }else if (currentFragment instanceof CredentialsFragment){
            loadFragment(mBankFragment, FragmentControllerDelegate.TRANSITION.BACK);
        }else if (currentFragment instanceof BondingFragment){
            loadFragment(mBankFragment, FragmentControllerDelegate.TRANSITION.BACK);
        }else if (currentFragment instanceof ErrorFragment){
            loadFragment(mBankFragment, FragmentControllerDelegate.TRANSITION.BACK);
        }else if (currentFragment instanceof SynchronizingFragment){
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
            case Welcome:
                loadFragment(mWelcomeFragment);
                break;
            case Bank:
                loadFragment(mBankFragment);
                break;
            case Credentials:
                loadFragment(mCredentialsFragment);
                break;
            case Bonding:
                loadFragment(mBondingFragment);
                break;
            case Error:
                loadFragment(mErrorFragment);
                break;
            case Synchronizing:
                loadFragment(mSynchronizingFragment);
                break;
        }
    }

}