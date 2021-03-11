package com.finerioconnect.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.finerioconnect.widget.component.AccountActivity;
import com.finerioconnect.widget.model.AccountWidget;

public class MainActivity extends AppCompatActivity {

    private AccountWidget accountWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountWidget = new AccountWidget();
        accountWidget.setCompanyName("your_company");
        accountWidget.setWidgetId("your_widget_id");
        accountWidget.setCustomerId("customer_id");
        accountWidget.setCustomerName("customer_name");
        accountWidget.setAutomaticFetching(true);
        accountWidget.setState("your_state");

        Button btnAccountView = findViewById(R.id.btnAccountView);
        btnAccountView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                intent.putExtra("AccountWidget", accountWidget);
                startActivity(intent);
            }
        });

    }
}
