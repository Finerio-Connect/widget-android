package com.finerioconnect.widget.credentials;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.finerioconnect.widget.remote.data.ResponseFinerioCredentialsErrors;
import com.google.gson.Gson;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.util.Objects;

import javax.crypto.Cipher;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.model.AccountWidget;
import com.finerioconnect.widget.model.Singleton;
import com.finerioconnect.widget.remote.ApiClient;
import com.finerioconnect.widget.remote.ApiService;
import com.finerioconnect.widget.remote.ServiceUrl;
import com.finerioconnect.widget.remote.data.Credentials;
import com.finerioconnect.widget.remote.data.FieldsMagicLink;
import com.finerioconnect.widget.remote.data.ResponseFinerioCredentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CredentialsPresenter implements ImplCredentialsPresenter {

    private final ApiService mApiService;
    private final ApiService mApiServiceFinerio;
    private final Context mContext;
    private final ImplCredentialsView mImplCredentialsView;

    public CredentialsPresenter (ImplCredentialsView implCredentialsView, Context context) {
        this.mApiService = ApiClient.getClient(ServiceUrl.API_FINERIO).create(ApiService.class);
        this.mApiServiceFinerio = ApiClient.getClient(ServiceUrl.MAGIC_LINK).create(ApiService.class);
        this.mImplCredentialsView = implCredentialsView;
        this.mContext = context;
    }

    @Override
    public void setCredentials(int bankId, String userName, String password, String securityCode) {
        AccountWidget widgetAccount = Singleton.getInstance().getDataWidget();
        Credentials credentials = new Credentials();
        credentials.setWidgetId(widgetAccount.getWidgetId());
        credentials.setCustomerId(widgetAccount.getCustomerId());
        credentials.setCustomerName(widgetAccount.getCustomerName());
        credentials.setAutomaticFetching(widgetAccount.getAutomaticFetching());
        credentials.setBankId(bankId);
        credentials.setSecurityCode( securityCode.isEmpty() ? null : encryptData(securityCode) );
        credentials.setState( encryptData(widgetAccount.getState()) );
        credentials.setUsername( encryptData(userName) );
        credentials.setPassword( encryptData(password) );

        mApiService.doCredential(credentials).enqueue(new Callback<ResponseFinerioCredentials>() {
            @Override
            public void onResponse(@NotNull Call<ResponseFinerioCredentials> call,
                                   @NotNull Response<ResponseFinerioCredentials> response) {
                if (response.body() != null){
                    mImplCredentialsView.showFinerioCredentials(response.body());
                }else{
                    try {
                        ResponseFinerioCredentialsErrors credentialsError =
                                new Gson().fromJson(Objects.requireNonNull(response.errorBody()).string(),
                                        ResponseFinerioCredentialsErrors.class);

                        onFailure(call, new Throwable(credentialsError.toString()));
                    } catch (IOException e) {
                        onFailure(call, new Throwable(mContext.getString(R.string.text_error_server)));
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseFinerioCredentials> call, @NotNull Throwable t) {
                call.cancel();
                mImplCredentialsView.showErrorMessage(t);
            }
        });
    }

    @Override
    public void getFieldByBankId(Integer bankId) {
        mApiServiceFinerio.doGetBankFields(bankId).enqueue(new Callback<FieldsMagicLink>() {
            @Override
            public void onResponse(@NotNull Call<FieldsMagicLink> call, @NotNull Response<FieldsMagicLink> response) {
                if (response.body() != null){
                    mImplCredentialsView.showFields(response.body().getData());
                }else{
                    onFailure(call, new Throwable(mContext.getString(R.string.text_error_server)));
                }
            }

            @Override
            public void onFailure(@NotNull Call<FieldsMagicLink> call, @NotNull Throwable t) {
                call.cancel();
                mImplCredentialsView.showErrorMessage(t);
            }
        });
    }

    public String encryptData(String text){
        try {
            InputStream stream = mContext.getAssets().open( "public_key.pem" );
            int size = stream.available();
            byte[] publicKeyBytes = new byte[ size ];
            stream.read( publicKeyBytes );
            stream.close();
            PEMParser pemParser = new PEMParser( new BufferedReader( new InputStreamReader(
                    new ByteArrayInputStream( publicKeyBytes ) ) ) );
            PublicKey publicKey = new JcaPEMKeyConverter().getPublicKey(
                    (SubjectPublicKeyInfo) pemParser.readObject() );
            Cipher cipher = Cipher.getInstance( "RSA/ECB/PKCS1PADDING" );
            cipher.init( Cipher.ENCRYPT_MODE, publicKey );
            byte[] encryptedBytes = cipher.doFinal( text.getBytes( "UTF-8" ) );
            String encryptedBase64 = Base64.encodeToString(encryptedBytes, Base64.NO_WRAP );
            Log.i( "RSA", encryptedBase64 );
            return encryptedBase64;
        } catch ( Exception e ) {
            e.printStackTrace();
            return "";
        }
    }

}