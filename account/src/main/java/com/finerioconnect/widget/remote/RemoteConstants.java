package com.finerioconnect.widget.remote;

import com.finerioconnect.widget.model.Singleton;
import com.finerioconnect.widget.utils.Tools;

public class RemoteConstants {

    public static final String API_ML = "https://magiclink-api.finerioconnect.com/";
    //public static final String API_F ="https://api-v2.finerio.mx/";
    public static final String API_F = validateUrl();

    //public static final String IMAGE_BANK_OFF = "https://cdn.finerio.mx/widget/bank_{bankId}_off.svg";
    //public static final String IMAGE_BANK_ON = "https://cdn.finerio.mx/widget/bank_{bankId}_on.svg";
    public static final String IMAGE_BANK_OFF = "https://cdn.finerio.mx/widget/bank_{bankId}_off.png";
    public static final String IMAGE_BANK_ON = "https://cdn.finerio.mx/widget/bank_{bankId}_on.png";
    public static final String IMAGE_BANK_GIF = "https://cdn.finerio.mx/widget/bank_{bankId}_help.gif";

    public static final String FIREBASE_URL = "https://finerio-android.firebaseio.com/";
    public static final String FIREBASE_PATH = "CtkFJ3subunSceVh7vUAPMB4TckRSv/";
    public static final String FIREBASE_PATH_ACCOUNT = "/accounts/";

    private static String validateUrl(){
        String urlBase = "https://api-v2-sandbox.finerio.mx/";
        if ( Tools.urlValidator(Singleton.getInstance().getDataWidget().getUrlServer()) ){
            urlBase = Singleton.getInstance().getDataWidget().getUrlServer();
        }
        return urlBase;
    }

}