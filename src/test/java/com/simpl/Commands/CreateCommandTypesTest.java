package com.simpl.Commands;

import com.simpl.Delegates.PaymentDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateCommandTypesTest {

    @Mock
    PaymentDelegate paymentDelegate;

    @Test
    public void shouldCallNewUserForUSERCommand() {

        CreateCommandTypes.USER.takeAction(new String[]{"new", "user", "u1","u1@gmail.com","1000"}, paymentDelegate);
        verify(paymentDelegate).newUser("u1","u1@gmail.com",Double.valueOf("1000") );
    }

    @Test
    public void shouldCallNewMerchantMethodForMERCHANTCommand() {

        CreateCommandTypes.MERCHANT.takeAction(new String[]{"new", "merchant","m1","m1@gmail.com","1.5%"}, paymentDelegate);
        verify(paymentDelegate).addMerchant("m1","m1@gmail.com",1.5D);
    }

    @Test
    public void shouldCallNewTransactionForTXNCommand() {
        CreateCommandTypes.TXN.takeAction(new String[]{"new", "txn","user1","m1","1000"}, paymentDelegate);
        verify(paymentDelegate).newTransaction("user1","m1",1000D);
    }
}
