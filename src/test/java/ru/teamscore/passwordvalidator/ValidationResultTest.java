package ru.teamscore.passwordvalidator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationResultTest {
    @Test
    void addErrorMessage_whenEmpty() {
        PasswordValidator.ValidationResult result = new PasswordValidator.ValidationResult();
        assertTrue(result.isValid());
        assertEquals(List.of(), result.getErrorMessages());

        result.addErrorMessage("Some error");

        assertFalse(result.isValid());
        assertEquals(List.of("Some error"), result.getErrorMessages());
    }

    @Test
    void addErrorMessage_whenHasError() {
        List<String> messages = new ArrayList<>();
        messages.add("Some error 1");
        PasswordValidator.ValidationResult result =
            new PasswordValidator.ValidationResult(false, messages);

        assertFalse(result.isValid());
        assertEquals(messages, result.getErrorMessages());

        result.addErrorMessage("Some error 2");

        assertFalse(result.isValid());
        assertEquals(List.of("Some error 1", "Some error 2"), result.getErrorMessages());
    }
}
