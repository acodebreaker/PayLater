package com.simpl.Commands;

import com.simpl.Delegates.PaymentDelegate;

public enum CreateCommandTypes implements Command {


    USER {
        @Override
        public void takeAction(String[] inputValues, PaymentDelegate paymentDelegate) {
            paymentDelegate.newUser(  inputValues[2],inputValues[3],Double.valueOf(inputValues[4])  );
        }
    },
    MERCHANT {
        @Override
        public void takeAction(String[] inputValues, PaymentDelegate paymentDelegate) {
           paymentDelegate.addMerchant(inputValues[2],inputValues[3],Double.valueOf(inputValues[4].replace("%","")));
        }
    },
    TXN {
        @Override
        public void takeAction(String[] inputValues, PaymentDelegate paymentDelegate) {
            paymentDelegate.newTransaction(inputValues[2],inputValues[3],Double.valueOf(inputValues[4])  );
        }
    }
}

