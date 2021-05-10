package com.finerioconnect.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.finerioconnect.widget.component.AccountActivity;
import com.finerioconnect.widget.model.AccountWidget;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AccountWidget accountWidget = new AccountWidget();

        // Required attributes
        accountWidget.setCompanyName("your_company");
        accountWidget.setWidgetId("Your_widget_idd");
        accountWidget.setCustomerName( "your_customer_name" );

        Button btnAccountView = findViewById(R.id.btnAccountView);
        btnAccountView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AccountActivity.class);
            intent.putExtra("AccountWidget", accountWidget);
            startActivity(intent);
        });

    }
}
