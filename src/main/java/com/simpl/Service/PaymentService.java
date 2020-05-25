package com.simpl.Service;

import com.simpl.Model.Merchant;
import com.simpl.Model.User;

import java.util.List;

public interface PaymentService {

    User newUser(String userName, String email, Double creditLimit);

    Merchant addMerchant(String merchantName, String email, Double discountPercent);

    String newTransaction(String userName, String merchantName,Double transactionAmount);

    String updateMerchant(String merchantName, Double newdiscountPercent);

    User payBack(String userName, Double payBackAmount);

    Double reportDiscount(String merchantName);

    Double reportDues(String userName);

    List<String> reportUsersAtCreditLimit();

    List<User> reportTotalDues();
}
