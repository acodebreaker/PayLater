package com.simpl.Commands;

import com.simpl.Delegates.PaymentDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UpdateCommandTypesTest {

    @Mock
    PaymentDelegate paymentDelegate;

    @Test
    public void shouldCallUpdateMerchantForMERCHANTCommand() {

        UpdateCommandTypes.MERCHANT.takeAction(new String[]{"update", "merchant","m1","1.5%"}, paymentDelegate);
        verify(paymentDelegate).updateMerchant("m1","1.5");
    }

}
