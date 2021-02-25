package com.finerioconnect.widget.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.finerioconnect.widget.R;

public class Tools {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void openBrowser(Activity mActivity, String url) {
        if ( url.startsWith("http://") || url.startsWith("https://") ){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mActivity.startActivity(browserIntent);
        }
    }

    public static void customTextView(Activity mActivity, TextView view) {
        SpannableStringBuilder spanTxt = new SpannableStringBuilder(
                "Al dar clic en Enviar información aceptas expresamente nuestros ");
        spanTxt.append("Términos de servicio");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                openBrowser(mActivity, mActivity.getString(R.string.terms_and_conditions_url));
            }
        }, spanTxt.length() - "Términos de servicio".length(), spanTxt.length(), 0);
        spanTxt.append(" así como nuestro ");
        //spanTxt.setSpan(new ForegroundColorSpan(Color.BLACK), 32, spanTxt.length(), 0);
        spanTxt.append(" Aviso de privacidad.");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                openBrowser(mActivity, mActivity.getString(R.string.privacy_terms_url));
            }
        }, spanTxt.length() - " Aviso de privacidad.".length(), spanTxt.length(), 0);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(spanTxt, TextView.BufferType.SPANNABLE);
    }

}
