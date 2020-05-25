package com.simpl.Commands;

import com.simpl.Delegates.PaymentDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PayBackCommandTypesTest {

    @Mock
    PaymentDelegate paymentDelegate;


    @Test
    public void shouldCallPaybackTransactionForPAYBACKCommand() {
        PayBackCommandTypes.PAYBACK.takeAction(new String[]{"payback","user1","1000"}, paymentDelegate);
        verify(paymentDelegate).payBack("user1",1000D);
    }
}
