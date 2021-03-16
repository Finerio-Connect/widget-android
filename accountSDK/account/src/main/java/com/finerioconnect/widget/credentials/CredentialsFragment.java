package com.finerioconnect.widget.credentials;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.finerioconnect.widget.R;
import com.finerioconnect.widget.fragment.ImplFragmentTransaction;
import com.finerioconnect.widget.remote.RemoteConstants;
import com.finerioconnect.widget.remote.data.Account;
import com.finerioconnect.widget.remote.data.Bank;
import com.finerioconnect.widget.remote.data.Field;
import com.finerioconnect.widget.remote.data.ItemDataBase;
import com.finerioconnect.widget.remote.data.ResponseFinerioCredentials;
import com.finerioconnect.widget.utils.AbstractFragment;
import com.finerioconnect.widget.utils.EventFragment;

import static com.finerioconnect.widget.utils.Tools.customTextView;

//import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
//import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;

public class CredentialsFragment extends AbstractFragment implements ImplCredentialsView,
        View.OnClickListener {

    private final ImplFragmentTransaction mImplFragmentTransaction;
    private final Bank mBank;
    private CredentialsPresenter presenter;
    private FieldsAdapter fieldsAdapter;
    private RecyclerView rvFields;
    private ProgressBar progressBarFields;

    public CredentialsFragment(ImplFragmentTransaction implFragmentTransaction, Bank bank) {
        mImplFragmentTransaction = implFragmentTransaction;
        mBank = bank;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_credentials, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new CredentialsPresenter(this, getContext());
        presenter.getFieldByBankId(mBank.getId());
        TextView titleBack = Objects.requireNonNull(getView()).findViewById(R.id.titleBack);
        TextView titleData = Objects.requireNonNull(getView()).findViewById(R.id.tvTitleData);
        titleData.setText(getString(R.string.create_credential_title, mBank.getName() ));
        Button btnSendCredentials = Objects.requireNonNull(getView()).findViewById(R.id.btnSendCredentials);
        titleBack.setOnClickListener(this);
        btnSendCredentials.setOnClickListener(this);
        ImageView ivLogoBank = Objects.requireNonNull(getView()).findViewById(R.id.ivLogoBank);
        loadImageBank(ivLogoBank);

        TextView tvTerms = Objects.requireNonNull(getView()).findViewById(R.id.tv_terms);
        customTextView(getActivity(), tvTerms);
    }

    void loadImageBank(ImageView ivLogoBank){
        Glide.with(this).load(mBank.getUrlImageOn()).into(ivLogoBank);
        /*if (mBank.getName().equals("Liverpool")){
            ivLogoBank.setImageResource(R.drawable.ic_liverpool_on);
            return;
        }
        GlideToVectorYou
                .init()
                .with(getActivity())
                .withListener(new GlideToVectorYouListener() {
                    @Override public void onLoadFailed() { }
                    @Override public void onResourceReady() { }
                })
                //.setPlaceHolder(placeholderLoading, placeholderError)
                .load(Uri.parse(mBank.getUrlImageOn()), ivLogoBank);
*/
    }

    @Override
    public void showErrorMessage(Throwable t) {
        progressBarFields.setVisibility(View.GONE);
        mImplFragmentTransaction.setFragmentTransaction(EventFragment.Error, t);
    }

    @Override
    public void showFields(List<Field> data) {
        progressBarFields = Objects.requireNonNull(getView()).findViewById(R.id.progressBarFields);
        progressBarFields.setVisibility(View.GONE);

        rvFields = getView().findViewById(R.id.rvFields);

        fieldsAdapter = new FieldsAdapter(getContext(), data);

        rvFields.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvFields.setAdapter(fieldsAdapter);
    }

    @Override
    public void showFinerioCredentials(ResponseFinerioCredentials responseFinerioCredentials) {
        mImplFragmentTransaction.setFragmentTransaction(EventFragment.Synchronizing, responseFinerioCredentials.getId());
        //dataBaseRealTime( responseFinerioCredentials.getId() );
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.titleBack) {
            mImplFragmentTransaction.setFragmentTransaction(EventFragment.Bank, mBank);
        } else if (id == R.id.btnSendCredentials) {
            validateForm();
        }
    }

    private void validateForm(){

        List<Field> fieldsList = fieldsAdapter.getArrayList();
        String user = "", password = "", securityCode = "";
        boolean flag = true;

        for (int i = 0; i < fieldsList.size(); i++) {

            Field field = fieldsList.get(i);

            if ( field.getUserName().isEmpty() && field.getRequired() ) {
                field.setError("Por favor ingrese "+field.getFriendlyName());
                flag = false;
            } else {
                field.setError("");

                switch (field.getName()){
                    case "username":
                        user = field.getUserName();
                        break;
                    case "password":
                        password = field.getUserName();
                        break;
                    case "securityCode":
                        securityCode = field.getUserName();
                        break;
                }

            }
        }

        fieldsAdapter = new FieldsAdapter(getContext(), fieldsList);
        rvFields.setAdapter(fieldsAdapter);

        if (flag){
            rvFields.setEnabled(false);
            hideProgress();
            presenter.setCredentials(mBank.getId(), user, password, securityCode);
        }

    }

    String credentialId;

    @Override
    protected String setFragmentTag() {
        return this.getClass().getSimpleName();
    }

        void dataBaseRealTime(String credentialId) {

            this.credentialId = credentialId ;

            FirebaseDatabase mDatabase =
                    FirebaseDatabase
                            .getInstance( RemoteConstants.FIREBASE_URL );

            DatabaseReference mDatabaseReference =
                    mDatabase
                            .getReference( RemoteConstants.FIREBASE_PATH + credentialId );

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    HashMap responseHashMap = (HashMap) dataSnapshot.getValue();
                    String gsonData = new Gson().toJson(responseHashMap);
                    ItemDataBase mItemDataBase = new Gson().fromJson(gsonData,ItemDataBase.class);
                    showData(mItemDataBase);
                }
                @Override
                public void onCancelled(@NotNull DatabaseError error) {
                    showProgress();
                    mImplFragmentTransaction.setFragmentTransaction(EventFragment.Error, new Throwable(error.getMessage()));
                }
            });

        }

        boolean createCredential = false;

        private void showData(ItemDataBase mItemDataBase) {
            String status = mItemDataBase.getStatus();

            switch (status){
                case "INTERACTIVE":
                    alertDialogToken();
                    showProgress();
                    break;
                case "FAILURE":
                    mImplFragmentTransaction.setFragmentTransaction(EventFragment.Error, new Throwable(mItemDataBase.getMessage()));
                    showProgress();
                    break;
                case "SUCCESS":
                    mImplFragmentTransaction.setFragmentTransaction(EventFragment.Synchronizing, null);
                    showProgress();
                    break;
                case "CREATED":
                    createNewInstance();
                    createCredential = true;
                    break;
                default: // CREATED & OTHER
                    break;
            }

        }

        private void createNewInstance() {
            if (createCredential){
                return;
            }
            FirebaseDatabase mDatabase =
                    FirebaseDatabase
                            .getInstance( RemoteConstants.FIREBASE_URL );

            DatabaseReference mDatabaseReference =
                    mDatabase
                            .getReference(
                                    RemoteConstants.FIREBASE_PATH +
                                            credentialId +
                                            RemoteConstants.FIREBASE_PATH_ACCOUNT );

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    HashMap responseHashMap = (HashMap) dataSnapshot.getValue();

                    if ( responseHashMap != null ){
                        String gsonData = new Gson().toJson( responseHashMap.values() );

                        Type type = new TypeToken<List<Account>>() {}.getType();

                        List<Account> yourList = new Gson().fromJson(gsonData, type);

                    }

                }
                @Override
                public void onCancelled(@NotNull DatabaseError error) {
                    //progressBarFields.setVisibility(View.GONE);
                    mImplFragmentTransaction.setFragmentTransaction(EventFragment.Error, new Throwable(error.getMessage()));
                }
            });
        }


    void hideProgress(){
        progressBarFields.setVisibility(View.VISIBLE);
        rvFields.setClickable(false);
        rvFields.setEnabled(false);
    }

    void showProgress(){
        progressBarFields.setVisibility(View.GONE);
        rvFields.setClickable(true);
        rvFields.setEnabled(true);
    }

    private void alertDialogToken() {
        Toast.makeText( getContext(), "", Toast.LENGTH_LONG ).show();
    }

    /*
    private void customTextView(TextView view) {
        SpannableStringBuilder spanTxt = new SpannableStringBuilder(
                "Al dar clic en Enviar información aceptas expresamente nuestros ");
        spanTxt.append("Términos de servicio");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                openBrowser(getActivity(), getString(R.string.terms_and_conditions_url));
            }
        }, spanTxt.length() - "Términos de servicio".length(), spanTxt.length(), 0);
        spanTxt.append(" así como nuestro ");
        //spanTxt.setSpan(new ForegroundColorSpan(Color.BLACK), 32, spanTxt.length(), 0);
        spanTxt.append(" Aviso de privacidad.");
        spanTxt.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                openBrowser(getActivity(), getString(R.string.privacy_terms_url));
            }
        }, spanTxt.length() - " Aviso de privacidad.".length(), spanTxt.length(), 0);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(spanTxt, TextView.BufferType.SPANNABLE);
    }
*/
}

