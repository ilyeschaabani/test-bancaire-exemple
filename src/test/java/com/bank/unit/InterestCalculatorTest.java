package com.bank.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterestCalculatorTest {

    @Test
    void calculatesSimpleInterestForOneYear() {
        double result = calculateSimpleInterest(1000.0, 0.05, 1.0);
        assertEquals(1050.0, result, 0.0001);
    }

    private double calculateSimpleInterest(double principal, double annualRate, double years) {
        return principal * (1.0 + annualRate * years);
    }
}