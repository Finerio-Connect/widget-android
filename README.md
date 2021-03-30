# SDK - Widgets

This is a repository of [Finerio](https://finerio.mx/)

## Download

You can download SDK.

[![](https://jitpack.io/v/Finerio-Connect/widget-android.svg)](https://jitpack.io/#Finerio-Connect/widget-android)

```bash

allprojects {
  repositories {
  ...
  maven { url 'https://jitpack.io' }
  }
}

...

dependencies {
    implementation 'com.github.Finerio-Connect:widget-android:Tag'
}

```

## ¿ How To Use SDK ?

Java Code
```java

    AccountWidget accountWidget = new AccountWidget();
    // Required attributes
    accountWidget.setCompanyName("your_company");
    accountWidget.setWidgetId("your_widget_id");
    //Optional attributes
    //accountWidget.setCustomerId(875L);
    accountWidget.setCustomerName("customer_name");
    accountWidget.setAutomaticFetching(true);
    accountWidget.setState("your_state");
    //url default Finerio Sandbox
    accountWidget.setUrlServer("url_production");

    Button btnAccountView = findViewById(R.id.btnAccountView);
    btnAccountView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AccountActivity.class);
            intent.putExtra("AccountWidget", accountWidget);
            startActivity(intent);
        }
    });
```
activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    <Button
        android:id="@+id/btnAccountView"
        style="@style/buttonAccount"
        android:text="@string/text_btn_add_accounts"
        android:layout_marginEnd="16dp"
        android:layout_marginStart="16dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"/>
</androidx.constraintlayout.widget.ConstraintLayout>
```

Android Manifest - XML
```xml
<activity
    android:name="com.finerioconnect.widget.component.AccountActivity"
/>
```

## 🎨 Color Customization

```xml
    <!-- Custom Color Customer -->
    <color name="main_color">your_color</color>
    <color name="main_background">your_color</color>
    <color name="main_text_color">your_color</color>
    <color name="terms_text_color">your_color</color>
```

## 📝 Text Customization
    
```xml
    <!-- Custom String Customer -->
    <string name="banks_title">your_text</string>
    <string name="create_credential_title">your_text</string>
    <string name="back_to_banks_label">your_text</string>
    <string name="submit_label">your_text</string>
    <string name="privacy_terms_url">your_text</string>
    <string name="terms_and_conditions_url">your_text</string>
    <string name="synchronization_title">your_text</string>

    <!-- Custom Lottie Url Customer -->
    <string name="url_lottie_account_loading">your_lottie_url</string>
    <string name="url_lottie_account_ready">your_lottie_url</string>
    <string name="url_lottie_syncing_loop">your_lottie_url</string>
    <string name="url_lottie_syncing_success">your_lottie_url</string>
    <string name="url_lottie_syncing_failure">your_lottie_url</string>
```

## 🔤 Fonts Customization
```xml
    <style name="AppTheme" >
        <!-- Customize Font Customer. -->
        <item name="fontFamily">@font/your_font</item>
        <item name="android:fontFamily">@font/your_font</item>
    </style>

```

## License
[Finerio SDK](https://finerio.mx/license)
