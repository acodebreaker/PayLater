package com.simpl.Delegates;

import com.simpl.Service.PaymentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PaymentDelegateImplTest {

    @InjectMocks
    PaymentDelegateImpl paymentDelegate;

    @Mock
    PaymentServiceImpl paymentService;

    @Test
    public void shouldCreateNewUser() {
        paymentDelegate.newUser("ankit","ankit@gmail",1000D);
        verify(paymentService).newUser("ankit","ankit@gmail",1000D);
    }

    @Test
    public void shouldCreateNewMerchant() {
        paymentDelegate.addMerchant("m1","m1@gmail",1.5);
        verify(paymentService).addMerchant("m1","m1@gmail",1.5);
    }

    @Test
    public void shouldCreateNewTransaction() {
        paymentDelegate.newTransaction("ankit","m1",400D);
        verify(paymentService).newTransaction("ankit","m1",400D);
    }

    @Test
    public void shouldUpdateMerchant() {
        paymentDelegate.updateMerchant("m1","1.5");
        verify(paymentService).updateMerchant("m1",1.5);
    }

    @Test
    public void shouldPaybackUser() {
        paymentDelegate.payBack("ankit",100D);
        verify(paymentService).payBack("ankit",100D);
    }

    @Test
    public void shouldReportDiscount() {
        paymentDelegate.reportDiscount("m1");
        verify(paymentService).reportDiscount("m1");
    }

    @Test
    public void shouldReportDues() {
        paymentDelegate.reportDues("ankit");
        verify(paymentService).reportDues("ankit");
    }

    @Test
    public void shouldReportUsersAtLimit() {
        paymentDelegate.reportUsersAtCreditLimit();
        verify(paymentService).reportUsersAtCreditLimit();
    }

    @Test
    public void shouldReportTotalDues() {
        paymentDelegate.reportTotalDues();
        verify(paymentService).reportTotalDues();
    }
}
