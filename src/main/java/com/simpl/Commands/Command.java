package com.simpl.Commands;

import com.simpl.Delegates.PaymentDelegate;

interface Command {
    void takeAction(String[] inputValues, PaymentDelegate paymentDelegate);
}
