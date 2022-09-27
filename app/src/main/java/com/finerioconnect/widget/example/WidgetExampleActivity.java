package com.finerioconnect.widget.example;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.finerioconnect.widget.core.api.data.Bank;
import com.finerioconnect.widget.core.component.BaseAccountView;
import com.finerioconnect.widget.databinding.PartialCustomToolbarBinding;
import com.finerioconnect.widget.example.databinding.ActivityWidgetExampleBinding;
import com.finerioconnect.widget.sections.bank.BankFragment;
import com.finerioconnect.widget.sections.bonding.BondingFragment;
import com.finerioconnect.widget.sections.credentials.CredentialsFragment;
import com.finerioconnect.widget.sections.models.CredentialAccount;
import com.finerioconnect.widget.sections.onboarding.OnboardingDetailFragment;
import com.finerioconnect.widget.sections.onboarding.OnboardingFragment;
import com.finerioconnect.widget.sections.synchronizing.SynchronizingFragment;
import com.finerioconnect.widget.sections.synchronizing.listeners.OnSynchronizingListener;
import com.finerioconnect.widget.utils.AbstractFragment;
import com.finerioconnect.widget.utils.nav.FragmentNavController;
import com.finerioconnect.widget.utils.nav.Transition;
import com.finerioconnect.widget.utils.theme.ThemeColorsModel;
import com.finerioconnect.widget.utils.theme.ThemeUtils;

public class WidgetExampleActivity extends AppCompatActivity implements BaseAccountView, OnSynchronizingListener {

    private BankFragment mBankFragment;
    private OnboardingFragment mOnboardingFragment;
    private OnboardingDetailFragment mOnboardingDetailFragment;

    private FragmentNavController mFragmentNavController;

    private ActivityWidgetExampleBinding mBinding;

    private PartialCustomToolbarBinding mBindingToolbar;

    private ThemeUtils mThemeUtils;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityWidgetExampleBinding.inflate(getLayoutInflater());
        mBindingToolbar = PartialCustomToolbarBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mFragmentNavController = new FragmentNavController(getSupportFragmentManager(), R.id.fragmentContainer);
        mBankFragment = new BankFragment();
        mOnboardingFragment = new OnboardingFragment();
        mOnboardingDetailFragment = new OnboardingDetailFragment();

        mThemeUtils = new ThemeUtils(this);

        setupStatusBar();
        setupToolbar();
        goOnboarding();
    }

    private void setupToolbar() {
        ThemeColorsModel colors = mThemeUtils.getColors();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mBindingToolbar.imageViewBackArrow.setOnClickListener(view1 -> onBackPressed());
        mBindingToolbar.imageViewBackArrow.setColorFilter(
                ContextCompat.getColor(this, colors.getRegularSizedText()));
        mBindingToolbar.textViewTitle.setTextColor(
                ContextCompat.getColor(this, colors.getRegularSizedText()));
        mBindingToolbar.toolbarBg.setBackgroundColor(
                ContextCompat.getColor(this, colors.getToolbarBg()));
    }

    private void setupStatusBar() {
        Window window = getWindow();
        mThemeUtils.setStatusBarColor(window);
    }

    @Override
    public void goOnboarding() {
        loadFragment(mOnboardingFragment, Transition.NONE);
        setTitle("");
    }

    @Override
    public void goOnboardingDetail() {
        loadFragment(mOnboardingDetailFragment, Transition.NONE);
        setTitle("");
    }

    @Override
    public void goBanks() {
        loadFragment(mBankFragment, Transition.NONE);
        setTitle(getString(com.finerioconnect.widget.R.string.title_banks));
    }

    @Override
    public void onSelectBank(final Bank bank, final CredentialsFragment credentialsFragment) {
        loadFragment(credentialsFragment, Transition.FORWARD);
        setTitle(getString(com.finerioconnect.widget.R.string.title_credentials));
    }

    @Override
    public void onSetCredentials(final String credentialId, final SynchronizingFragment synchronizingFragment) {
        synchronizingFragment.setSynchronizingListener(this);
        loadFragment(synchronizingFragment, Transition.FORWARD);
        setTitle(getString(com.finerioconnect.widget.R.string.title_synchronizing));
    }

    @Override
    public void onError(final BondingFragment bondingFragment) {
        loadFragment(bondingFragment, Transition.FORWARD);
        setTitle(getString(com.finerioconnect.widget.R.string.title_error_message));
    }

    @Override
    public void onCredentialsCreated(final BondingFragment bondingFragment) {
        loadFragment(bondingFragment, Transition.FORWARD);
        setTitle(getString(com.finerioconnect.widget.R.string.title_success_message));
    }

    @Override
    public void restart() {
        loadFragment(mBankFragment, Transition.BACK);
        setTitle(getString(com.finerioconnect.widget.R.string.title_banks));
    }

    @Override
    public void finishWidget() {
        finish();
    }

    @Override
    public void onBackPressed() {
        final AbstractFragment currentFragment = mFragmentNavController.getCurrentFragment();
        if (currentFragment instanceof CredentialsFragment || currentFragment instanceof BondingFragment) {
            loadFragment(mBankFragment, Transition.BACK);
            setTitle(getString(com.finerioconnect.widget.R.string.title_banks));
        } else if (currentFragment instanceof SynchronizingFragment) {
            //Cannot return view while it is synchronizing
        } else {
            super.onBackPressed();
        }
    }

    private void loadFragment(final AbstractFragment fragment, final Transition transition) {
        mFragmentNavController.loadFragment(fragment, transition, false);
    }

    @Override
    public void accountCreated(final CredentialAccount credentialAccount) {
        Log.d("SynchronizingListener",
                "Credential id: " + credentialAccount.getCredentialId() +
                        ", AccountName: " + credentialAccount.getName() +
                        ", Status: " + credentialAccount.getStatus());
    }

    private void setTitle(final String title) {
        if (!title.isEmpty()) mBindingToolbar.textViewTitle.setText(title);
    }

}
