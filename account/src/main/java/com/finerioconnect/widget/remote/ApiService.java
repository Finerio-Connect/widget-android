package com.finerioconnect.widget.remote;

import com.finerioconnect.widget.remote.data.BanksMagicLink;
import com.finerioconnect.widget.remote.data.Credentials;
import com.finerioconnect.widget.remote.data.FieldsMagicLink;
import com.finerioconnect.widget.remote.data.ResponseFinerioCredentials;
import com.finerioconnect.widget.remote.data.UserToken;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("banks")
    Call<BanksMagicLink> doGetBanks();

    @Headers("Content-Type: application/json")
    @GET("banks/{bankId}/fields")
    Call<FieldsMagicLink> doGetBankFields(@Path("bankId") Integer bankId);

    @Headers("Content-Type: application/json")
    @POST("j2GVbQs3kkcBEttuPWZihSFZkoWnIDwQt2zsGRmQZoitHzMllB")
    Call<ResponseFinerioCredentials> doCredential(@Body Credentials credentials);

    @Headers("Content-Type: application/json")
    @PUT("p8U55qGnTMLb7HQzZfCjwcQARtVrrgyt8he9fQKz3KgAFPbAwb")
    Call<ResponseBody> putUserToken(@Body UserToken userToken);

}