package com.finerioconnect.widget.example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.finerioconnect.widget.core.FinerioConnectWidget;
import com.finerioconnect.widget.core.component.AccountActivity;
import com.finerioconnect.widget.databinding.PartialCustomToolbarBinding;
import com.finerioconnect.widget.example.databinding.ActivityMainBinding;
import com.finerioconnect.widget.shared.Enums;
import com.finerioconnect.widget.utils.theme.ThemeColorsModel;
import com.finerioconnect.widget.utils.theme.ThemeUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private PartialCustomToolbarBinding mBindingToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mBindingToolbar = PartialCustomToolbarBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setup();
        initToolbar();
    }

    private void initToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        final ThemeColorsModel colors = new ThemeUtils(this).getColors();

        mBindingToolbar.imageViewBackArrow.setVisibility(View.GONE);
        mBindingToolbar.textViewTitle.setText(getString(com.finerioconnect.widget.R.string.app_name));
        mBindingToolbar.textViewTitle.setTextColor(
                ContextCompat.getColor(this, colors.getRegularSizedText()));
        mBindingToolbar.toolbarBg.setBackgroundColor(
                ContextCompat.getColor(this, colors.getToolbarBg()));
        mBinding.clBackground.setBackgroundColor(ContextCompat.getColor(
                this, colors.getBackgroundView()));

    }

    private void setup() {
        FinerioConnectWidget finerioConnectWidget = FinerioConnectWidget.getShared();
        // Required attributes
        finerioConnectWidget.setCompanyName("Finerio");
        finerioConnectWidget.setWidgetId("Widget Id");
        finerioConnectWidget.setEnvironment(Enums.Environment.Sandbox);
        finerioConnectWidget.setCustomerName("rene.perez@finerio.mx");

        finerioConnectWidget.config(this);

        Button btnAccountView = findViewById(R.id.btnAccountView);
        Button btnCustomWidgetExample = findViewById(R.id.btnCustomWidgetExample);
        btnAccountView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AccountActivity.class);
            startActivity(intent);
        });
        btnCustomWidgetExample.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WidgetExampleActivity.class);
            startActivity(intent);
        });
    }

}