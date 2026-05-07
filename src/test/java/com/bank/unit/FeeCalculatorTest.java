package com.bank.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FeeCalculatorTest {

    @Test
    void calculatesMonthlyFeeWithAndWithoutWaiver() {
        assertEquals(10.0, calculateMonthlyFee(1000.0, 10.0, 2000.0), 0.0001);
        assertEquals(0.0, calculateMonthlyFee(1000.0, 10.0, 5000.0), 0.0001);
    }

    private double calculateMonthlyFee(double balance, double baseFee, double waiverThreshold) {
        if (balance >= waiverThreshold) {
            return 0.0;
        }
        return baseFee;
    }
}