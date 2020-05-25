package com.simpl.Output;

import com.simpl.Commands.CreateCommandTypes;
import com.simpl.Commands.PayBackCommandTypes;
import com.simpl.Commands.ReportCommandTypes;
import com.simpl.Commands.UpdateCommandTypes;
import com.simpl.Delegates.PaymentDelegate;
import com.simpl.Delegates.PaymentDelegateImpl;

public class OutputGenerator {
    static PaymentDelegate paymentDelegate = new PaymentDelegateImpl();

    public static void generate(String inputCommand) {
        String[] inputs = inputCommand.split(" ");

        if(inputs[0].equals("new"))
        CreateCommandTypes.valueOf(inputs[1].toUpperCase()).takeAction(inputs, paymentDelegate);

        if(inputs[0].equals("report"))
            ReportCommandTypes.valueOf(inputs[1].toUpperCase().replace("-","_")).takeAction(inputs,paymentDelegate);

        if(inputs[0].equals("update"))
            UpdateCommandTypes.valueOf(inputs[1].toUpperCase()).takeAction(inputs,paymentDelegate);

        if(inputs[0].equals("payback"))
            PayBackCommandTypes.valueOf(inputs[0].toUpperCase().replace("-","_")).takeAction(inputs,paymentDelegate);

    }

}