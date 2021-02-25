package com.finerioconnect.widget.utils;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivityFragment extends AppCompatActivity {

    private FragmentControllerDelegate mFCDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFCDelegate = new FragmentControllerDelegate(getSupportFragmentManager(), getFragmentContainerId());
        initPersistFragments();
    }

    @IdRes
    protected abstract int getFragmentContainerId();

    protected abstract void initPersistFragments();

    protected void loadFragment(@NonNull AbstractFragment fragment) {
        mFCDelegate.loadFragment(fragment, getFragmentContainerId(), FragmentControllerDelegate.TRANSITION.NONE, false);
    }

    protected void loadFragmentWithBackStack(@NonNull AbstractFragment fragment) {
        mFCDelegate.loadFragment(fragment, getFragmentContainerId(), FragmentControllerDelegate.TRANSITION.NONE, true);
    }

    protected void loadFragment(@NonNull AbstractFragment fragment, @IdRes int idContainer) {
        mFCDelegate.loadFragment(fragment, idContainer, FragmentControllerDelegate.TRANSITION.NONE, false);
    }

    protected void loadFragment(@NonNull AbstractFragment fragment, boolean addToBackStack) {
        mFCDelegate.loadFragment(fragment, getFragmentContainerId(), FragmentControllerDelegate.TRANSITION.NONE, addToBackStack);
    }

    protected void loadFragment(@NonNull AbstractFragment fragment, @IdRes int idContainer, boolean addToBackStack) {
        mFCDelegate.loadFragment(fragment, idContainer, FragmentControllerDelegate.TRANSITION.NONE, addToBackStack);
    }

    public void loadFragment(@NonNull AbstractFragment fragment, @NonNull FragmentControllerDelegate.TRANSITION direction) {
        mFCDelegate.loadFragment(fragment, getFragmentContainerId(), direction, false);
    }

    protected void loadFragment(@NonNull AbstractFragment fragment, @IdRes int idContainer, @NonNull FragmentControllerDelegate.TRANSITION direction) {
        mFCDelegate.loadFragment(fragment, idContainer, direction, false);
    }

    protected void loadFragment(@NonNull AbstractFragment fragment, @NonNull FragmentControllerDelegate.TRANSITION direction,
                                boolean addToBackStack) {
        mFCDelegate.loadFragment(fragment, getFragmentContainerId(), direction, addToBackStack);
    }

    protected AbstractFragment getCurrentFragment() {
        return mFCDelegate.getCurrentFragment();
    }

    protected AbstractFragment getCurrentFragment(@IdRes int idContainer) {
        return mFCDelegate.getCurrentFragment(idContainer);
    }

    protected void removeAllFragment() {
        mFCDelegate.removeAllFragment();
    }

    public void removeCurrentFragment() {
        mFCDelegate.removeCurrentFragment();
    }

}