package com.simpl.Commands;

import com.simpl.Delegates.PaymentDelegate;

public enum UpdateCommandTypes implements Command {


    MERCHANT {
        @Override
        public void takeAction(String[] inputValues, PaymentDelegate paymentDelegate) {
            paymentDelegate.updateMerchant(inputValues[2],inputValues[3].replace("%",""));
        }
    }
}
