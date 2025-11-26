package ru.teamscore.passwordvalidator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordValidatorTest {
    private void isValidPassword(String password, String username, boolean result) {
        // Act
        boolean validationResult = PasswordValidator.isValidPassword(password, username);

        // Assert
        assertEquals(result, validationResult);
    }

    @ParameterizedTest
    @DisplayName("Scenarios with invalid passwords")
    @MethodSource("provideValidatePassword")
    void validatePassword(String password, String username,
                          ValidationResult expected) {
        ValidationResult result = PasswordValidator.validatePassword(password, username);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideValidatePassword() {
        return Stream.of(
            Arguments.of("Test6789", "TestUser", new ValidationResult(true, List.of())),
            Arguments.of("abcdefg", "TestUser",
                new ValidationResult(false, List.of("Длина пароля меньше 8 символов", "Пароль " +
                    "должен содержать хотя бы 1 цифру", "Пароль должен содержать хотя бы 1 символ" +
                    " в верхнем регистре"))),
            Arguments.of("AbCdEfGhIjKlMn", "TestUser",
                new ValidationResult(false, List.of("Пароль должен содержать хотя бы 1 цифру"))),
            Arguments.of("P@$$W0RD", "TestUser", new ValidationResult(false,
                List.of("Пароль должен содержать хотя бы 1 символ в нижнем " +
                    "регистре"))),
            Arguments.of(")(*&56nm", "TestUser", new ValidationResult(false, List.of("Пароль " +
                "должен содержать хотя бы 1 символ в верхнем регистре"))),
            Arguments.of("TestUser", "TestUser", new ValidationResult(false, List.of("Пароль " +
                "должен содержать хотя бы 1 цифру", "Пароль не должен совпадать с именем " +
                "пользователя"))),
            Arguments.of("qwerty 1234", "TestUser", new ValidationResult(false,
                List.of("Пароль должен содержать хотя бы 1 символ в верхнем регистре",
                    "Пароль не должен содержать пробелов или кавычек")))
        );
    }

    @ParameterizedTest
    @DisplayName("Valid password is accepted")
    @ValueSource(strings =
        {"Test6789", "1R2g3T4k5Y", "0123456789aA", "_TestUser74"})
    void validPassword(String password) {
        isValidPassword(password, "TestUser", true);
    }

    @ParameterizedTest
    @DisplayName("Password is less 8 symbols")
    @ValueSource(strings =
        {"abcdefg", "_ _ _ ", "1234", "1", ""})
    void tooShortPassword(String password) {
        isValidPassword(password, "TestUser", false);
    }

    @ParameterizedTest
    @DisplayName("Password has no digits")
    @ValueSource(strings =
        {"asdfghjkl", "__TgTg__", "AbCdEfGhIjKlMn"})
    void noDigitsPassword(String password) {
        isValidPassword(password, "TestUser", false);
    }

    @ParameterizedTest
    @DisplayName("Password has no lower case letters")
    @ValueSource(strings =
        {"QWERTYU2", "01234ABCD", "P@$$W0RD"})
    void noLowerCasePassword(String password) {
        isValidPassword(password, "TestUser", false);
    }

    @ParameterizedTest
    @DisplayName("Password has no upper case letters")
    @ValueSource(strings =
        {"qwerty1234", ")(*&56nm", "password5"})
    void noUpperCasePassword(String password) {
        isValidPassword(password, "TestUser", false);
    }

    @Test
    @DisplayName("Password equals username")
    void equalsUsernamePassword() {
        String username = "TestUser1";
        isValidPassword(username, username, false);
    }

    @ParameterizedTest
    @DisplayName("Password has spaces or quotes")
    @ValueSource(strings =
        {"qwerty 1234", "_&*^\tBg79", "\"34jdfgER", " \t\r\n\"Rg6*"})
    void hasSpacesQuotesPassword(String password) {
        isValidPassword(password, "TestUser", false);
    }
}