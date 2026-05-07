package com.bank.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransferValidatorTest {

    @Test
    void validatesAllowedTransferAmounts() {
        assertTrue(isTransferAllowed(100.0, 500.0));
        assertFalse(isTransferAllowed(0.0, 500.0));
        assertFalse(isTransferAllowed(750.0, 500.0));
    }

    private boolean isTransferAllowed(double amount, double maxAmount) {
        return amount > 0.0 && amount <= maxAmount;
    }
}