package com.simpl.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class User {

    String userName;
    String email;
    Double creditLimit;

    public Double getDues() {
        return dues;
    }

    public void setDues(Double dues) {
        this.dues = dues;
    }

    Double dues;


    public String getUserName() {
        return userName;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

}
