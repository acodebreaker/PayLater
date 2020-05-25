package com.simpl.Service;

import com.simpl.Model.Merchant;
import com.simpl.Model.User;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentServiceImpl implements PaymentService{

Map<String,Merchant> merchantMap=new HashMap<>();
Map<String,User>userMap=new HashMap<>();
List<User>userList=new ArrayList<>();

    @Override
    public User newUser(String userName, String email, Double creditLimit) {

       User newUser= User.builder().userName(userName).email(email).creditLimit(creditLimit).dues(0D).build();
       userList.add(newUser);
       userMap.put(userName,newUser);
       return newUser;
    }

    @Override
    public Merchant addMerchant(String merchantName, String email, Double discountPercent) {
        Merchant newMerchant=Merchant.builder().merchantName(merchantName).email(email).discountPercent(discountPercent).totalAmount(0D).build();
        merchantMap.put(merchantName,newMerchant);
        return newMerchant;
    }

    @Override
    public String newTransaction(String userName, String merchantName, Double transactionAmount) {
        User user= userMap.get(userName);
        Double dues= user.getDues();

            if (dues + transactionAmount  > user.getCreditLimit()) {
                return "rejected! (reason: credit limit)";
            }

        user.setDues(dues + transactionAmount);
        Merchant merchant=merchantMap.get(merchantName);
        double discountRate=merchant.getDiscountPercent();
        merchant.setTotalAmount( merchant.getTotalAmount()  + ((100 - discountRate) * transactionAmount)/100  );
        merchantMap.put(merchantName,merchant);
        userMap.put(userName,user );
        userList.stream().filter(x-> x.getUserName().equals(userName)).map(x-> user);
        return "success!";
    }

    @Override
    public String updateMerchant(String merchantName, Double newDiscountPercent) {
        Merchant merchant= merchantMap.get(merchantName);
        merchant.setDiscountPercent(newDiscountPercent);
        merchantMap.put(merchantName,merchant);
        return newDiscountPercent.toString();
    }

    @Override
    public User payBack(String userName, Double payBackAmount) {
        User user= userMap.get(userName);
        user.setDues(user.getDues() - payBackAmount);
        userMap.put(userName,user);
        userList.stream().filter(x-> x.getUserName().equals(userName)).map(x-> user);
        return user;
    }

    @Override
    public Double reportDiscount(String merchantName) {
        return merchantMap.get(merchantName).getDiscountPercent();
    }

    @Override
    public Double reportDues(String userName) {
        return Math.abs(userMap.get(userName).getDues());
    }

    @Override
    public List<String> reportUsersAtCreditLimit() {
    return userList.stream().filter(x -> Math.abs(x.getDues()) >= x.getCreditLimit() ).map(x-> x.getUserName()).collect(Collectors.toList());
    }

    @Override
    public List<User> reportTotalDues() {
    return userList;
    }
}
