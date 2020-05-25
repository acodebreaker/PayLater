package com.simpl.Delegates;

import com.simpl.Model.Merchant;
import com.simpl.Model.User;
import com.simpl.Service.PaymentService;
import com.simpl.Service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentDelegateImpl implements PaymentDelegate {

    PaymentService paymentService=new PaymentServiceImpl();

    @Override
    public void newUser(String userName, String email, Double creditLimit) {
        User user=paymentService.newUser(userName, email, creditLimit);
        if(user==null) return;
        System.out.println(user.getUserName() + " (" + user.getCreditLimit() + ")");
    }

    @Override
    public void addMerchant(String merchantName, String email, Double discountPercent) {
        Merchant merchant=paymentService.addMerchant(merchantName, email, discountPercent);
        if(merchant==null) return;
        System.out.println(merchant.getMerchantName() + " (" + merchant.getDiscountPercent() + "%)");
    }

    @Override
    public void newTransaction(String userName, String merchantName, Double transactionAmount) {
    System.out.println(paymentService.newTransaction(userName, merchantName, transactionAmount));
    }

    @Override
    public void updateMerchant(String merchantName, String newdiscountPercent) {
    String newDiscount=paymentService.updateMerchant(merchantName,Double.valueOf(newdiscountPercent));
    System.out.println("interest-rate: " + newDiscount +"%");
    }

    @Override
    public void payBack(String userName, Double payBackAmount) {
        User user=paymentService.payBack(userName,payBackAmount);
        if(user==null) return;
        System.out.println(user.getUserName() + "(dues: " +user.getDues() + ")" );
    }

    @Override
    public void reportDiscount(String merchantName) {
        System.out.println(paymentService.reportDiscount(merchantName));
    }

    @Override
    public void reportDues(String userName) {
    System.out.println(paymentService.reportDues(userName));
    }

    @Override
    public void reportUsersAtCreditLimit() {
        List<String> usersAtCreditLimit= paymentService.reportUsersAtCreditLimit();
        System.out.println(usersAtCreditLimit.stream().collect(Collectors.joining("\n")));
    }

    @Override
    public void reportTotalDues() {
        List<User>users=paymentService.reportTotalDues();
        printTotalDues(users);

    }

    private void printTotalDues(List<User>users){
        Double total=0D;
        for(User user:users)
        {
            System.out.println(user.getUserName() + ": " + user.getDues());
            total+=user.getDues();
        }
        System.out.println("total: "+ total);
    }
}
