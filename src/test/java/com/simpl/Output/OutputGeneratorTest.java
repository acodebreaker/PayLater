package com.simpl.Output;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OutputGeneratorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testGenerateCreateUser() {
        OutputGenerator.generate("new user ankit ankit@gmail 1000");
        Assert.assertEquals(outContent.toString(), "ankit (1000.0)\n");
    }

    @Test
    public void testGenerateCreateMerchant() {
        OutputGenerator.generate("new merchant m1 m1@gmail 2%");
        Assert.assertEquals(outContent.toString(), "m1 (2.0%)\n");
    }

    @Test
    public void testGenerateNewTransaction() {
        OutputGenerator.generate("new user ankit ankit@gmail 1000");
        OutputGenerator.generate("new merchant m1 m1@gmail 2%");
        OutputGenerator.generate("new txn ankit m1 200");
        OutputGenerator.generate("new txn ankit m1 1000");
        String[] expected = outContent.toString().split("\n");

        Assert.assertEquals(expected[0], "ankit (1000.0)");
        Assert.assertEquals(expected[1], "m1 (2.0%)");
        Assert.assertEquals(expected[2], "success!");
        Assert.assertEquals(expected[3],"rejected! (reason: credit limit)");

    }

    @Test
    public void testReportUsersAtLimit() {
        OutputGenerator.generate("new user ankit ankit@gmail 1000");
        OutputGenerator.generate("new merchant m1 m1@gmail 2%");
        OutputGenerator.generate("new txn ankit m1 1000");
        OutputGenerator.generate("report users-at-credit-limit");

        String[] expected = outContent.toString().split("\n");

        Assert.assertEquals(expected[0], "ankit (1000.0)");
        Assert.assertEquals(expected[1], "m1 (2.0%)");
        Assert.assertEquals(expected[2], "success!");
        Assert.assertEquals(expected[3],"ankit");
    }

    @Test
    public void testUpdateMerchant() {
        OutputGenerator.generate("new user ankit ankit@gmail 1000");
        OutputGenerator.generate("new merchant m1 m1@gmail 2%");
        OutputGenerator.generate("new txn ankit m1 1000");
        OutputGenerator.generate("update merchant m1 1.5%");

        String[] expected = outContent.toString().split("\n");

        Assert.assertEquals(expected[0], "ankit (1000.0)");
        Assert.assertEquals(expected[1], "m1 (2.0%)");
        Assert.assertEquals(expected[2], "success!");
        Assert.assertEquals(expected[3],"interest-rate: 1.5%");
    }

    @Test
    public void testPayBack() {
        OutputGenerator.generate("new user ankit ankit@gmail 1000");
        OutputGenerator.generate("new merchant m1 m1@gmail 2%");
        OutputGenerator.generate("new txn ankit m1 200");
        OutputGenerator.generate("payback ankit 50");

        String[] expected = outContent.toString().split("\n");

        Assert.assertEquals(expected[0], "ankit (1000.0)");
        Assert.assertEquals(expected[1], "m1 (2.0%)");
        Assert.assertEquals(expected[2], "success!");
        Assert.assertEquals(expected[3],"ankit(dues: 150.0)");
    }

    @Test
    public void testReportDiscount() {
        OutputGenerator.generate("new user ankit ankit@gmail 1000");
        OutputGenerator.generate("new merchant m1 m1@gmail 2%");
        OutputGenerator.generate("report discount m1");

        String[] expected = outContent.toString().split("\n");

        Assert.assertEquals(expected[0], "ankit (1000.0)");
        Assert.assertEquals(expected[1], "m1 (2.0%)");
        Assert.assertEquals(expected[2],"2.0");
    }

    @Test
    public void testReportDues() {
        OutputGenerator.generate("new user ankit ankit@gmail 1000");
        OutputGenerator.generate("new merchant m1 m1@gmail 2%");
        OutputGenerator.generate("new txn ankit m1 200");
        OutputGenerator.generate("report dues ankit");
        String[] expected = outContent.toString().split("\n");
        Assert.assertEquals(expected[0], "ankit (1000.0)");
        Assert.assertEquals(expected[1], "m1 (2.0%)");
        Assert.assertEquals(expected[2], "success!");
        Assert.assertEquals(expected[3],"200.0");
    }




}
