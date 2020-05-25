package com.simpl.Commands;

import com.simpl.Delegates.PaymentDelegate;

public enum ReportCommandTypes implements Command {


    USERS_AT_CREDIT_LIMIT {
        @Override
        public void takeAction(String[] inputValues, PaymentDelegate paymentDelegate) {
            paymentDelegate.reportUsersAtCreditLimit();
        }
    },
    DISCOUNT {
        @Override
        public void takeAction(String[] inputValues, PaymentDelegate paymentDelegate) {
            paymentDelegate.reportDiscount(inputValues[2]);
        }
    },
    TOTAL_DUES {
        @Override
        public void takeAction(String[] inputValues, PaymentDelegate paymentDelegate) {
            paymentDelegate.reportTotalDues();
        }
    },
    DUES {
        @Override
        public void takeAction(String[] inputValues, PaymentDelegate paymentDelegate) {
            paymentDelegate.reportDues(inputValues[2]);
        }
    }

}
