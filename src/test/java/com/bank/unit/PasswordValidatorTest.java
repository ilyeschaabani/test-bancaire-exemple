package com.bank.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    @Test
    void validatesStrongPasswordRules() {
        assertTrue(isStrongPassword("Banking@123"));
        assertFalse(isStrongPassword("password"));
        assertFalse(isStrongPassword("Short1!"));
    }

    private boolean isStrongPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUppercase = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLowercase = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecialCharacter = password.chars().anyMatch(character -> !Character.isLetterOrDigit(character));
        return hasUppercase && hasLowercase && hasDigit && hasSpecialCharacter;
    }
}