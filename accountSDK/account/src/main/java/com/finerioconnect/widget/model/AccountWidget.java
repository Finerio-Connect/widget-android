package com.finerioconnect.widget.model;

import java.io.Serializable;

public class AccountWidget implements Serializable {

    // Required attributes
    private String widgetId;
    private String customerName;

    //Optional attributes
    private String companyName;
    private String customerId;
    private Boolean automaticFetching;
    private String state;

    //Colors and fonts
    private String mainColor;
    private String mainFont;
    private String backgroundColor;
    private String mainTextColor;
    private String termsTextColor;

    //Titles, legends and links
    private String banksTitle;
    private String createCredentialTitle;
    private String backToBanksLabel;
    private String submitLabel;
    private String privacyTermsUrl;
    private String termsAndConditionsUrl;
    private String synchronizationTitle;

    //Animations
    private String syncingAnimation;
    private String loadingAccountAnimation;
    private String accountReadyAnimation;
    private String successAnimation;
    private String failureAnimation;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Boolean getAutomaticFetching() {
        return automaticFetching;
    }

    public void setAutomaticFetching(Boolean automaticFetching) {
        this.automaticFetching = automaticFetching;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public String getMainFont() {
        return mainFont;
    }

    public void setMainFont(String mainFont) {
        this.mainFont = mainFont;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getMainTextColor() {
        return mainTextColor;
    }

    public void setMainTextColor(String mainTextColor) {
        this.mainTextColor = mainTextColor;
    }

    public String getTermsTextColor() {
        return termsTextColor;
    }

    public void setTermsTextColor(String termsTextColor) {
        this.termsTextColor = termsTextColor;
    }

    public String getBanksTitle() {
        return banksTitle;
    }

    public void setBanksTitle(String banksTitle) {
        this.banksTitle = banksTitle;
    }

    public String getCreateCredentialTitle() {
        return createCredentialTitle;
    }

    public void setCreateCredentialTitle(String createCredentialTitle) {
        this.createCredentialTitle = createCredentialTitle;
    }

    public String getBackToBanksLabel() {
        return backToBanksLabel;
    }

    public void setBackToBanksLabel(String backToBanksLabel) {
        this.backToBanksLabel = backToBanksLabel;
    }

    public String getSubmitLabel() {
        return submitLabel;
    }

    public void setSubmitLabel(String submitLabel) {
        this.submitLabel = submitLabel;
    }

    public String getPrivacyTermsUrl() {
        return privacyTermsUrl;
    }

    public void setPrivacyTermsUrl(String privacyTermsUrl) {
        this.privacyTermsUrl = privacyTermsUrl;
    }

    public String getTermsAndConditionsUrl() {
        return termsAndConditionsUrl;
    }

    public void setTermsAndConditionsUrl(String termsAndConditionsUrl) {
        this.termsAndConditionsUrl = termsAndConditionsUrl;
    }

    public String getSynchronizationTitle() {
        return synchronizationTitle;
    }

    public void setSynchronizationTitle(String synchronizationTitle) {
        this.synchronizationTitle = synchronizationTitle;
    }

    public String getSyncingAnimation() {
        return syncingAnimation;
    }

    public void setSyncingAnimation(String syncingAnimation) {
        this.syncingAnimation = syncingAnimation;
    }

    public String getLoadingAccountAnimation() {
        return loadingAccountAnimation;
    }

    public void setLoadingAccountAnimation(String loadingAccountAnimation) {
        this.loadingAccountAnimation = loadingAccountAnimation;
    }

    public String getAccountReadyAnimation() {
        return accountReadyAnimation;
    }

    public void setAccountReadyAnimation(String accountReadyAnimation) {
        this.accountReadyAnimation = accountReadyAnimation;
    }

    public String getSuccessAnimation() {
        return successAnimation;
    }

    public void setSuccessAnimation(String successAnimation) {
        this.successAnimation = successAnimation;
    }

    public String getFailureAnimation() {
        return failureAnimation;
    }

    public void setFailureAnimation(String failureAnimation) {
        this.failureAnimation = failureAnimation;
    }

}
