package com.finerioconnect.widget.utils;

import androidx.annotation.AnimRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.finerioconnect.widget.R;

public class FragmentControllerDelegate {

    @IdRes
    private int idContainerFragments = -1;
    private FragmentTransaction mFragmentTransaction;
    private final FragmentManager mFragmentManager;

    public FragmentControllerDelegate(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public FragmentControllerDelegate(FragmentManager fragmentManager, int idContainerFragments) {
        mFragmentManager = fragmentManager;
        this.idContainerFragments = idContainerFragments;
    }

    public enum TRANSITION {
        FORWARD(R.anim.slide_from_right, R.anim.slide_to_left),
        BACK(R.anim.slide_from_left, R.anim.slide_to_right),
        FADE(android.R.anim.fade_in, android.R.anim.fade_out),
        NONE(0, 0);

        private final int enterAnimation;
        private final int exitAnimation;

        TRANSITION(@AnimRes int enterAnimation, @AnimRes int exitAnimation) {
            this.enterAnimation = enterAnimation;
            this.exitAnimation = exitAnimation;
        }

        public int getEnterAnimation() {
            return enterAnimation;
        }

        public int getExitAnimation() {
            return exitAnimation;
        }
    }

    public void loadFragment(@NonNull AbstractFragment fragment, @IdRes int idContainer, @NonNull TRANSITION direction,
                             boolean addToBackStack) {
        if (getCurrentFragment() != null) {
            if (getCurrentFragment().getFragmentTag().equalsIgnoreCase(fragment.getFragmentTag())) {
                return;
            }
        }
        mFragmentTransaction = mFragmentManager.beginTransaction();
        if (!direction.equals(TRANSITION.NONE)) {
            mFragmentTransaction.setCustomAnimations(direction.getEnterAnimation(), direction.getExitAnimation(), direction.getEnterAnimation(), direction.getExitAnimation());
        }
        mFragmentTransaction.replace(idContainer, fragment, fragment.getFragmentTag());
        if (addToBackStack) {
            mFragmentTransaction.addToBackStack(fragment.getFragmentTag());
        }
        mFragmentTransaction.commitAllowingStateLoss();
    }

    public void hideFragment(@NonNull AbstractFragment fragment) {
        if (getCurrentFragment() != null) {
            if (getCurrentFragment().getFragmentTag().equalsIgnoreCase(fragment.getFragmentTag())) {
                return;
            }
        }
        mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction.hide(fragment);
        mFragmentTransaction.commit();
    }

    public AbstractFragment getCurrentFragment() {
        if (idContainerFragments != -1) {
            return getCurrentFragment(idContainerFragments);
        }
        return null;
    }

    public AbstractFragment getCurrentFragment(@IdRes int idContainer) {
        return (AbstractFragment) mFragmentManager.findFragmentById(idContainer);
    }

    public void removeAllFragment() {
        //mFragmentManager.popBackStack ();
        while (mFragmentManager.getBackStackEntryCount() > 0) {
            mFragmentManager.popBackStackImmediate();
        }
    }

    public void removeFragment() {
        //mFragmentManager.popBackStack ();
        while (mFragmentManager.getBackStackEntryCount() > 0) {
            mFragmentManager.popBackStackImmediate();
        }
    }

    public void removeCurrentFragment() {
        mFragmentManager.popBackStackImmediate ();
        mFragmentManager.popBackStack ();
    }
}