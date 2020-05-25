package com.simpl.Commands;

import com.simpl.Delegates.PaymentDelegate;

public enum PayBackCommandTypes implements Command {


    PAYBACK {
        @Override
        public void takeAction(String[] inputValues, PaymentDelegate paymentDelegate) {
            paymentDelegate.payBack(inputValues[1],Double.valueOf(inputValues[2]));
        }

    }
}
