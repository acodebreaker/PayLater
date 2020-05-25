package com.simpl.Delegates;

public interface PaymentDelegate {

    void newUser(String userName, String email, Double creditLimit);

    void addMerchant(String merchantName, String email, Double discountPercent);

    void newTransaction(String userName, String merchantName,Double transactionAmount);

    void updateMerchant(String merchantName, String newdiscountPercent);

    void payBack(String userName, Double payBackAmount);

    void reportDiscount(String merchantName);

    void reportDues(String userName);

    void reportUsersAtCreditLimit();

    void reportTotalDues();

}
