package com.finerioconnect.widget.utils;

import android.content.Context;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractFragment extends Fragment {

    protected FragmentControllerDelegate mFCDelegate;
    protected String mFragmentTag;
    protected abstract String setFragmentTag();

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mFragmentTag = setFragmentTag() != null ? setFragmentTag() : getClass().getSimpleName();
        mFCDelegate = new FragmentControllerDelegate(getChildFragmentManager(), getFragmentContainer());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFCDelegate = null;
    }

    protected AbstractFragment getCurrentFragment() {
        return mFCDelegate.getCurrentFragment();
    }

    protected AbstractFragment getCurrentFragment(@IdRes int idContainer) {
        return mFCDelegate.getCurrentFragment(idContainer);
    }

    @IdRes
    protected int getFragmentContainer() {
        return 0;
    }

    public String getFragmentTag() {
        return mFragmentTag;
    }

    protected void loadFragment(@NonNull AbstractFragment fragment) {
        mFCDelegate.loadFragment(fragment, getFragmentContainer(), FragmentControllerDelegate.TRANSITION.NONE, false);
    }

    protected void loadFragment(@NonNull AbstractFragment fragment, @IdRes int idContainer) {
        mFCDelegate.loadFragment(fragment, idContainer, FragmentControllerDelegate.TRANSITION.NONE, false);
    }

    protected void loadFragment(@NonNull AbstractFragment fragment, boolean addToBackStack) {
        mFCDelegate.loadFragment(fragment, getFragmentContainer(), FragmentControllerDelegate.TRANSITION.NONE, addToBackStack);
    }

    protected void loadFragment(@NonNull AbstractFragment fragment, @IdRes int idContainer, boolean addToBackStack) {
        mFCDelegate.loadFragment(fragment, idContainer, FragmentControllerDelegate.TRANSITION.NONE, addToBackStack);
    }

    protected void loadFragment(@NonNull AbstractFragment fragment, @NonNull FragmentControllerDelegate.TRANSITION direction) {
        mFCDelegate.loadFragment(fragment, getFragmentContainer(), direction, false);
    }

    protected void loadFragment(@NonNull AbstractFragment fragment, @IdRes int idContainer,
                                @NonNull FragmentControllerDelegate.TRANSITION direction) {
        mFCDelegate.loadFragment(fragment, idContainer, direction, false);
    }

    protected void loadFragment(@NonNull AbstractFragment fragment, @NonNull FragmentControllerDelegate.TRANSITION direction,
                                boolean addToBackStack) {
        mFCDelegate.loadFragment(fragment, getFragmentContainer(), direction, addToBackStack);
    }

    protected void hideFragment(@NonNull AbstractFragment fragment) {
        mFCDelegate.hideFragment(fragment);
    }

}