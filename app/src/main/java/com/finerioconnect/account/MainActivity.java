package com.finerioconnect.account;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.finerioconnect.widget.component.AccountActivity;
import com.finerioconnect.widget.model.AccountWidget;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup(){
        AccountWidget accountWidget = new AccountWidget();
        // Required attributes
        accountWidget.setCompanyName("Finerio");
        accountWidget.setWidgetId("pparKeszQYwBF64A8WsWab5VDnVdE8QDnVCp2pgVubJRxyNU46");
        accountWidget.setCustomerName( "irving.arellano@finerio.mx" );
        Button btnAccountView = findViewById(R.id.btnAccountView);
        btnAccountView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AccountActivity.class);
            intent.putExtra("AccountWidget", accountWidget);
            startActivity(intent);
        });
    }

}