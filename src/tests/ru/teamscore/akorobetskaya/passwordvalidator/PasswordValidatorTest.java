package ru.teamscore.akorobetskaya.passwordvalidator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    @Test
    @DisplayName("Valid password is accepted")
    public void validPassword() {
        // Arrange
        String password = "Test6789";
        String username = "TestUser";

        // Act
        boolean validationResult = PasswordValidator.isValidPassword(password, username);

        // Assert
        assertEquals(true, validationResult);
    }
}