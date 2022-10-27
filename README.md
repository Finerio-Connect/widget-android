# Finerio Connect Android Widget



<a href="https://github.com/Finerio-Connect/widget-android/releases"><img src="https://img.shields.io/github/v/release/Finerio-Connect/widget-android" /></a>
![Language](https://img.shields.io/badge/Language-Java-orange.svg)
![Language](https://img.shields.io/badge/Language-Kotlin-purple.svg)

Finerio Connect Android Widget for the API Finerio Connect 2.0 reduces friction in the process of requesting their online banking credentials from your users, by taking care of the implementation and improving user experience.

## 🤖 Installation

### Prerequisites

- You must have an active account with Finerio Connect 2.0

### Installation

1. Download the [latest Widget version](https://github.com/Finerio-Connect/widget-android/releases) zip file.
2. Unzip the com folder (containing the Widget local maven dependencies) and put it in ~/.m2/repository/.
3. Add mavenLocal() as repository in your root level build.gradle file.

```gradle
    allprojects {
        repositories {
            mavenLocal()
            // Rest of the repositories
        }
    }
```

### Using Gradle

4. Add it in your root `build.gradle` at the end of repositories:

```gradle
buildscript {
    ext.kotlin_version = "1.5.30"
    ...
    dependencies {
         classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
         ...
    }
}
```

5. Edit your app `build.gradle` and specify the dependencies:

```gradle
implementation 'com.finerioconnect.widget:widget-android:2.5.6'
```

6. Edit your app `settings.gradle` and specify:

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url "https://zendesk.jfrog.io/artifactory/repo"
        }
        maven {
            url 'C:/Users/your_user_name/.m2/repository'
        }
        google()
        mavenCentral()
    }
}
```

7. Edit your app `build.properties` and specify the properties:

```
android.enableJetifier=true
```

8. Set the java compiler to Java 8 or higher. In your app-level build.gradle:
```
    compileOptions {
       sourceCompatibility = JavaVersion.VERSION_1_8
       targetCompatibility = JavaVersion.VERSION_1_8
    }
```

## ⚙️ Use

Java Code :

```java
    FinerioConnectWidget finerioConnectWidget = FinerioConnectWidget.getShared();
        // Required attributes
        finerioConnectWidget.setCompanyName("your_company");
        finerioConnectWidget.setWidgetId("your_widget_id");
        //Optional attributes
        finerioConnectWidget.setCustomerId(875L);
        finerioConnectWidget.setCustomerName("customer_name");
        //Environment
        finerioConnectWidget.setEnvironment(Enums.Environment.Production);
        //Optional automatic fetching
        finerioConnectWidget.setAutomaticFetching(true);
        //Optional state
        finerioConnectWidget.setState("your_state");
        //Optional country code for bank search, MX is default value
        finerioConnectWidget.setCountryCode("CO");
        //Optional param to show or hide country list
        finerioConnectWidget.setShowCountryOptions(true);
        //Optional show or hide bank types
        finerioConnectWidget.setShowBankTypeOptions(true);
        //Optional bank types, PERSONAL is default value. (PERSONAL, BUSINESS, FISCAL)
        finerioConnectWidget.setBankType(Enums.BankType.PERSONAL);
        //Optional theme mode, LIGHT is default value.(LIGHT, DARK, AUTOMATIC)
        finerioConnectWidget.setDarkMode(DarkMode.LIGHT);
        //Optional show or hide chat option
        finerioConnectWidget.setShowChat(true);
        //Optional show or hide onboarding screen
        finerioConnectWidget.setShowOnboarding(true);
        //Optional configure custom onboarding screens
        finerioConnectWidget.setOnboarding(custom_onboarding_object);
        //Required method to initial main components
        finerioConnectWidget.config(this);

        //Optional listener to get process synchronizing data from activity
        AccountActivity.setSynchronizingListener(credentialAccount -> {});

        Button btnAccountView = findViewById(R.id.btnAccountView);
        btnAccountView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        startActivity(intent);
        }
        });
```

Kotlin Code :

```kotlin
    val finerioConnectWidget = FinerioConnectWidget.getShared()
// Required attributes
finerioConnectWidget.companyName = "your_company"
finerioConnectWidget.widgetId = "your_widget_id"

//Optional attributes
finerioConnectWidget.environment = Enums.Environment.Sandbox
finerioConnectWidget.customerName = "your_customer_name"
finerioConnectWidget.countryCode = "CO"
finerioConnectWidget.setAutomaticFetching = true
finerioConnectWidget.setState = "your_state"
finerioConnectWidget.showCountryOptions = true
finerioConnectWidget.showBankTypeOptions = true
finerioConnectWidget.bankType = Enums.BankType.PERSONAL
finerioConnectWidget.darkMode = DarkMode.LIGHT
finerioConnectWidget.config(this)

findViewById<Button>(R.id.btnAccountView)
    .setOnClickListener {
        val intent = Intent(this@MainActivity, AccountActivity::class.java)
        startActivity(intent)
    }
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

<uses-permission android:name="android.permission.INTERNET"/>

<activity
android:name="com.finerioconnect.widget.core.component.AccountActivity"
/>
```

### Custom implementation

Widget fragments to use in own activity:

#### Bank fragment

Java Code :

```java
BankFragment mBankFragment = new BankFragment();
```

Kotlin Code :

```kotlin
val mBankFragment = BankFragment()
```

#### Credentials fragment

Java Code :

```java
CredentialsFragment credentialsFragment = new CredentialsFragment();
        credentialsFragment.setBank(BankObject);
```

Kotlin Code :

```kotlin
val credentialsFragment = CredentialsFragment()
credentialsFragment.setBank(BankObject)
```

#### Synchronizing fragment

Java Code :

```java
SynchronizingFragment synchronizingFragment = new SynchronizingFragment();
        synchronizingFragment.setCredentialId("CredentialId");
        synchronizingFragment.setSynchronizingListener(this);
```

Kotlin Code :

```kotlin
val synchronizingFragment = SynchronizingFragment()
synchronizingFragment.setCredentialId("CredentialId")
synchronizingFragment.setSynchronizingListener(this)
```

#### Bonding fragment

Java Code :

```java
BondingFragment mBondingFragment = new BondingFragment(null);
```

Kotlin Code :

```kotlin
val mBondingFragment = BondingFragment(null)
```

Listener mandatory to implement on activity, it help to manage flow and provides next step of flow and required data:

```java
public interface BaseAccountView {
    void goBanks();
    void onSelectBank(final Bank bank, final CredentialsFragment credentialsFragment);
    void onSetCredentials(final String credentialId, final SynchronizingFragment synchronizingFragment);
    void onError(final BondingFragment bondingFragment);
    void onCredentialsCreated(final BondingFragment bondingFragment);
    void restart();
    void finishWidget();
}
```

You can implement the `BaseAccountView` in your activity to receive callbacks

```java
...
public class MainActivity extends AppCompatActivity implements BaseAccountView {
...
```

Listener to get process synchronizing data(credential id, account id, account name and status):

```java
public interface OnSynchronizingListener {
    void accountCreated(final CredentialAccount credentialAccount);
}
```

## Customization

### 🎨 Customize colors

#### Light Mode:

```xml
     // Tint color for the icon rounded by a circle.
<color name="circleIconTint">your_color</color>
    // Background color for the circle that contains an icon.
<color name="circleIconBackground">your_color</color>
    // Background color for the buttons that performs a next or continue flow action.
<color name="buttonActiveBackground">your_color</color>
    // Tint color for the title of buttons that performs a next or continue flow action.
<color name="buttonActiveText">your_color</color>
    // Background color for the buttons that performs an exit or stops the flow action.
<color name="buttonPassiveBackground">your_color</color>
    // Tint color for the title of buttons that performs an exit or stops the flow action.
<color name="buttonPassiveText">your_color</color>
    // Background color used in all the views
<color name="backgroundView">your_color</color>
    // Tint color commonly used for the paragraph texts or normal texts.
<color name="regularSizedText">your_color</color>
    // Tint color used in phrases or titles to distinguish from others descriptions.
<color name="mediumSizedText">your_color</color>
    // Tint color for the phrases or paragraphs with a thin text
<color name="liteText">your_color</color>
    // Tint color used in linked texts that redirects to a web site or a view within the flow.
<color name="linkedText">your_color</color>
    // Background color used in the edittext.
<color name="fieldsBackground">your_color</color>
    // Border tint  color used in the edittext.
<color name="fieldsBorder">your_color</color>
    // Tint color used in the placeholders of the edittext.
<color name="fieldsPlaceholder">your_color</color>
    // Tint color used in the right icons of the edittext.
<color name="fieldsRightIcon">your_color</color>
    // Background color for segmented controls.
<color name="segmentedControlBackground">your_color</color>
    // Background color for the active part of segmented controls.
<color name="segmentedControlActiveItem">your_color</color>
    // Tint color for the text in the dropdown menu.
<color name="dropDownMenuTint">your_color</color>
    // Border color of the banner component.
<color name="bannerBorder">your_color</color>
    // Tint color for the success icon shown after a correct synchronization process.
<color name="successIconTint">your_color</color>
    // Tint color for the failure icon shown after a failed synchronization process.
<color name="failureIconTint">your_color</color>
    // Tint color used in the cell separators of list.
<color name="cellSeparator">your_color</color>
    // Tint color used in the icons of disclosure indicators within the cells of list.
<color name="cellDisclosureIndicator">your_color</color>
    // Tint color used in the current dot of the page control of onboarding components.
<color name="pageDotActive">your_color</color>
    // Tint color used in the unselected dots of the page control of onboarding components.
<color name="pageDotInactive">your_color</color>
    // Background color used in toolbar
<color name="toolbarBg">your_color</color>

    // Background color used in fab
<color name="fcZendeskIconBackground">your_color</color>
<color name="fcZendeskIconTint">your_color</color>
```

#### Dark mode:

```xml
    // Tint color for the icon rounded by a circle.
<color name="circleIconTintDark">your_color</color>
    // Background color for the circle that contains an icon.
<color name="circleIconBackgroundDark">your_color</color>
    // Background color for the buttons that performs a next or continue flow action.
<color name="buttonActiveBackgroundDark">your_color</color>
    // Tint color for the title of buttons that performs a next or continue flow action.
<color name="buttonActiveText">your_color</color>
    // Background color for the buttons that performs an exit or stops the flow action.
<color name="buttonPassiveBackgroundDark">your_color</color>
    // Tint color for the title of buttons that performs an exit or stops the flow action.
<color name="buttonPassiveTextDark">your_color</color>
    // Background color used in all the views
<color name="backgroundViewDark">your_color</color>
    // Tint color commonly used for the paragraph texts or normal texts.
<color name="regularSizedTextDark">your_color</color>
    // Tint color used in phrases or titles to distinguish from others descriptions.
<color name="mediumSizedTextDark">your_color</color>
    // Tint color for the phrases or paragraphs with a thin text
<color name="liteTextDark">your_color</color>
    // Tint color used in linked texts that redirects to a web site or a view within the flow.
<color name="linkedTextDark">your_color</color>
    // Background color used in the edittext.
<color name="fieldsBackgroundDark">your_color</color>
    // Border tint  color used in the edittext.
<color name="fieldsBorderDark">your_color</color>
    // Tint color used in the placeholders of the edittext.
<color name="fieldsPlaceholderDark">your_color</color>
    // Tint color used in the right icons of the edittext.
<color name="fieldsRightIconDark">your_color</color>
    // Background color for segmented controls.
<color name="segmentedControlBackgroundDark">your_color</color>
    // Background color for the active part of segmented controls.
<color name="segmentedControlActiveItemDark">your_color</color>
    // Tint color for the text in the dropdown menu.
<color name="dropDownMenuTintDark">your_color</color>
    // Border color of the banner component.
<color name="bannerBorderDark">your_color</color>
    // Tint color for the success icon shown after a correct synchronization process.
<color name="successIconTintDark">your_color</color>
    // Tint color for the failure icon shown after a failed synchronization process.
<color name="failureIconTintDark">your_color</color>
    // Tint color used in the cell separators of list.
<color name="cellSeparatorDark">your_color</color>
    // Tint color used in the icons of disclosure indicators within the cells of list.
<color name="cellDisclosureIndicatorDark">your_color</color>
    // Tint color used in the current dot of the page control of onboarding components.
<color name="pageDotActiveDark">your_color</color>
    // Tint color used in the unselected dots of the page control of onboarding components.
<color name="pageDotInactiveDark">your_color</color>
    // Background color used in toolbar
<color name="toolbarBgDark">your_color</color>

    // Background color used in fab
<color name="fcZendeskIconBackgroundDark">your_color</color>
<color name="fcZendeskIconTintDark">your_color</color>
```

### 📝 Text Customization

```xml
<!-- Custom strings banks view -->
<string name="fcBanksHeaderTitle">your_text</string>
<string name="fcBanksHeaderSubtitle">your_text</string>
<string name="fcSelectCountryLabel">your_text</string>
<string name="fcPersonalBankType">your_text</string>
<string name="fcBusinessBankType">your_text</string>
<string name="fcFiscalBankType">your_text</string>
<string name="fcTitleWithoutBanks">your_text</string>

    <!-- Custom strings Credentials view -->
<string name="fcCredentialsHeaderTitle">your_text</string>
<string name="fcCredentialsHeaderSubtitle">your_text</string>
<string name="fcCredentialsInfo">your_text</string>
<string name="fcCredentialsDisclaimer">your_text</string>
<string name="fcTermsAndConditions">your_text</string>
<string name="fcSubmitLabel">your_text</string>
<string name="fcMustAcceptTerms">your_text</string>

    <!-- Custom strings Synchronizing view -->
<string name="fcSyncHeaderTitle">your_text</string>
<string name="fcSyncHeaderSubtitle">your_text</string>
<string name="fcEncryptingData">your_textyour_text</string>

    <!-- Custom strings Synchronizing view -->
<string name="fcBondingHeaderTitle">¡Gracias!</string>
<string name="fcBondingHeaderTitleSuccess">your_text</string>
<string name="fcBondingHeaderTitleFail">your_text</string>
<string name="fcBondingDescriptionSuccess">your_text</string>
<string name="fcBondingDescriptionFail">your_text</string>
<string name="fcAccept">your_text</string>
```

### 🔤 Fonts Customization

Default font: Poppins

```xml
    <style name="AppTheme" >
    <!-- Customize Font Customer. -->
    <item name="fontFamily">@font/your_font</item>
    <item name="android:fontFamily">@font/your_font</item>
</style>
```
