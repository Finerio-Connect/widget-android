package com.finerioconnect.widget.synchronizing;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.fragment.ImplFragmentTransaction;
import com.finerioconnect.widget.model.Singleton;
import com.finerioconnect.widget.remote.RemoteConstants;
import com.finerioconnect.widget.remote.data.Account;
import com.finerioconnect.widget.remote.data.ItemDataBase;
import com.finerioconnect.widget.remote.data.UserToken;
import com.finerioconnect.widget.utils.AbstractFragment;
import com.finerioconnect.widget.utils.EventFragment;
import com.finerioconnect.widget.utils.ImplGenericResult;

public class SynchronizingFragment extends AbstractFragment implements View.OnClickListener, ImplGenericResult {

    private final String mCredentialId;
    private RecyclerView rvAccount;
    private List<Account> accountList;
    private AccountAdapter accountAdapter;
    private final ImplFragmentTransaction mImplFragmentTransaction;
    private final SynchronizingPresenter synchronizingPresenter;
    private DatabaseReference mDatabaseReference;
    private ValueEventListener valueEventListener;

    public SynchronizingFragment(ImplFragmentTransaction implFragmentTransaction, String credentialId) {
        this.mImplFragmentTransaction = implFragmentTransaction;
        this.mCredentialId = credentialId;
        this.synchronizingPresenter = new SynchronizingPresenter(this);
        //this.mCredentialId = "0d8a79ee-174c-414e-a3b7-8946d6567d62";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_synchronizing, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setup();
    }

    private void setup() {
        View view = getView();
        assert view != null;

        TextView titleBack = view.findViewById(R.id.titleBack);
        titleBack.setOnClickListener(this);
        Button btnSynCancel = view.findViewById(R.id.btnSynCancel);
        btnSynCancel.setOnClickListener(this);

        rvAccount = view.findViewById(R.id.rvAccount);
        rvAccount.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAccount.setItemAnimator(new DefaultItemAnimator());

        this.dataBaseRealTime();

    }

    @Override
    protected String setFragmentTag() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.titleBack){
            mImplFragmentTransaction.setFragmentTransaction(EventFragment.Bank, null);
        }else if (id == R.id.btnSynCancel){
            mImplFragmentTransaction.setFragmentTransaction(EventFragment.Welcome, null);
        }
    }

    void dataBaseRealTime() {

        FirebaseDatabase mDatabase =
                FirebaseDatabase
                        .getInstance( RemoteConstants.FIREBASE_URL );

        mDatabaseReference =
                mDatabase
                        .getReference( RemoteConstants.FIREBASE_PATH + mCredentialId );

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                HashMap responseHashMap = (HashMap) dataSnapshot.getValue();
                String gsonData = new Gson().toJson(responseHashMap);
                ItemDataBase mItemDataBase = new Gson().fromJson(gsonData,ItemDataBase.class);
                if (mItemDataBase != null){
                    showData(mItemDataBase);
                }
            }
            @Override
            public void onCancelled(@NotNull DatabaseError error) {
                mImplFragmentTransaction.setFragmentTransaction(EventFragment.Error,
                        new Throwable(error.getMessage()));
            }
        };

        mDatabaseReference.addValueEventListener(valueEventListener);

    }

    boolean createCredential = false;

    private void showData(ItemDataBase mItemDataBase) {

        if (mItemDataBase.getStatus() == null){
            mImplFragmentTransaction.setFragmentTransaction(EventFragment.Error , 
                    new Throwable( mItemDataBase.getMessage() ) );
        }else{
            String status = String.valueOf(mItemDataBase.getStatus());

            Log.d("======", "ItemDataBase: "+mItemDataBase.toString());
            Log.d("======", "Status: "+status);

            switch (status){
                case "INTERACTIVE":
                    alertDialogToken( mItemDataBase );
                    break;
                case "FAILURE":
                    removeListener();
                    mImplFragmentTransaction.setFragmentTransaction(EventFragment.Error,
                            new Throwable(mItemDataBase.getMessage()));
                    break;
                case "SUCCESS":
                    removeListener();
                    mImplFragmentTransaction.setFragmentTransaction(EventFragment.Bonding, null);
                    break;
                case "CREATED":
                    createNewInstance();
                    createCredential = true;
                    break;
                default: // OTHER
                    break;
            }
        }

    }

    private void removeListener(){
        if(valueEventListener != null){
            mDatabaseReference.removeEventListener(valueEventListener);
        }
    }

    private void createNewInstance() {
        if (!createCredential){
            FirebaseDatabase mDatabase =
                    FirebaseDatabase
                            .getInstance( RemoteConstants.FIREBASE_URL );

            DatabaseReference mDatabaseReference =
                    mDatabase
                            .getReference(
                                    RemoteConstants.FIREBASE_PATH +
                                            mCredentialId +
                                            RemoteConstants.FIREBASE_PATH_ACCOUNT );

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    HashMap responseHashMap = (HashMap) dataSnapshot.getValue();

                    if ( responseHashMap != null ){

                        Log.d("======", "onDataChange: "+ responseHashMap.toString());

                        List<Account> accountListFirebase = getListAccountByHashMap( responseHashMap );

                        if ( accountAdapter == null ){
                            accountList = accountListFirebase;
                            accountAdapter = new AccountAdapter( accountList );
                            rvAccount.setAdapter( accountAdapter );
                        }else{
                            accountAdapter.updateList(accountListFirebase);
                            accountAdapter.notifyDataSetChanged();
                        }

                    }

                }
                @Override
                public void onCancelled(@NotNull DatabaseError error) {
                    mImplFragmentTransaction.setFragmentTransaction(EventFragment.Error, 
                            new Throwable(error.getMessage()));
                }
            });
        }
    }

    private List<Account> getListAccountByHashMap(HashMap<String, String> responseHashMap){

        List<Account> accountArrayList = new ArrayList<>();

        for (Object idString : responseHashMap.keySet()){

            String idAccount = idString.toString();
            String data = String.valueOf( responseHashMap.get( idAccount ) ) ;

            Account account = new Account();
            account.setId( idAccount );

            assert data != null;
            String[] parts = data
                    .replace("}", "")
                    .replace("{", "")
                    .split(",");

            for(String part : parts){
                String[] accountString = part.split("=");

                String key = accountString[0].trim();
                String value = accountString[1].trim();

                if ( key.equals("name") ){
                    account.setName(value);
                }else if ( key.equals("status") ){
                    account.setStatus(value);
                }

            }

            accountArrayList.add(account);
        }

        Log.d("======", "getListAccountByHashMap: "+accountArrayList.toString());

        return accountArrayList;

    }

    private void alertDialogToken(ItemDataBase mItemDataBase) {

        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setTitle("Solicitud Token");
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        builder.setView(customLayout);

        if (!mItemDataBase.getBankToken().isEmpty()){
            customLayout.findViewById(R.id.tv_description).setVisibility( View.VISIBLE );
            TextView tvToken = customLayout.findViewById(R.id.tv_token);
            tvToken.setVisibility( View.VISIBLE );
            tvToken.setText( mItemDataBase.getBankToken() );
            TextView tvTitle = customLayout.findViewById(R.id.tv_title);
            tvTitle.setText( "Por favor introduce este número en tu token" );
        }


        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText = customLayout.findViewById(R.id.et_token);
                sendDialogDataToActivity(editText.getText().toString());
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCancelable( false );
        dialog.show();
    }

    private void sendDialogDataToActivity(String token) {
        UserToken userToken = new UserToken();
        userToken.setId( mCredentialId );
        userToken.setToken( token );
        userToken.setWidgetId( Singleton.getInstance().getDataWidget().getWidgetId() );
        synchronizingPresenter.setToken(userToken);
    }

    @Override
    public void onSuccess(String message) {
        //Toast.makeText(getActivity(), message , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable throwable) {
        mImplFragmentTransaction.setFragmentTransaction(EventFragment.Error, throwable);
    }

}