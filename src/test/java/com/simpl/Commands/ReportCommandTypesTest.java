package com.simpl.Commands;

import com.simpl.Delegates.PaymentDelegate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportCommandTypesTest {

    @Mock
    PaymentDelegate paymentDelegate;

    @Test
    public void shouldCallCreditLimitForUSERSATCREDITLIMITCommand() {

        ReportCommandTypes.USERS_AT_CREDIT_LIMIT.takeAction(new String[]{"report", "users_at_credit_limit"}, paymentDelegate);
        verify(paymentDelegate).reportUsersAtCreditLimit();
    }

    @Test
    public void shouldCallReportDiscountForDISCOUNTCommand() {

        ReportCommandTypes.DISCOUNT.takeAction(new String[]{"report", "discount","m1"}, paymentDelegate);
        verify(paymentDelegate).reportDiscount("m1");
    }

    @Test
    public void shouldCallTotalDuesForTOTALDUESCommand() {
        ReportCommandTypes.TOTAL_DUES.takeAction(new String[]{"report", "total-dues"}, paymentDelegate);
        verify(paymentDelegate).reportTotalDues();
    }

    @Test
    public void shouldCallDuesForDUESCommand() {
        ReportCommandTypes.DUES.takeAction(new String[]{"report", "dues","u1"}, paymentDelegate);
        verify(paymentDelegate).reportDues("u1");
    }
}
