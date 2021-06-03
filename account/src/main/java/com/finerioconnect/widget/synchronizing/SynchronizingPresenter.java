package com.finerioconnect.widget.synchronizing;

import com.finerioconnect.widget.remote.ApiClient;
import com.finerioconnect.widget.remote.ApiService;
import com.finerioconnect.widget.remote.ServiceUrl;
import com.finerioconnect.widget.remote.data.UserToken;
import com.finerioconnect.widget.utils.ImplGenericResult;

import org.jetbrains.annotations.NotNull;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SynchronizingPresenter {

    private final ApiService mApiService;
    private final ImplGenericResult mImplGenericResult;

    public SynchronizingPresenter ( ImplGenericResult implGenericResult ) {
        this.mApiService = ApiClient.getClient(ServiceUrl.API_FINERIO).create(ApiService.class);
        this.mImplGenericResult = implGenericResult;
    }

    void setToken(UserToken tokenData){
        mApiService.putUserToken(tokenData).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                mImplGenericResult.onSuccess( "Success: "+response.toString() );
            }
            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                call.cancel();
                mImplGenericResult.onFailure( t );
            }
        });
    }

}