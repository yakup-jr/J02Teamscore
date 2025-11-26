package ru.teamscore.passwordvalidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Проверка пароля на сложность.
 * Пароль должен отвечать следующим требованиям:
 * - не менее 8 символов в длину
 * - содержит строчные, заглавные буквы и цифры
 * - не должен совпадать с имененем пользователя
 * - не должен содержать пробельных символов, табуляции и кавычек
 */
public class PasswordValidator {

    private PasswordValidator() {
    }

    /**
     * Validate password validation result.
     *
     * @param password the password
     * @param userName the user name
     * @return the validation result
     */
    public static ValidationResult validatePassword(String password, String userName) {
        ValidationResult validationResult = new ValidationResult();

        if (password.length() < 8) {
            validationResult.addErrorMessage("Длина пароля меньше 8 символов");
        }
        if (!hasDigits(password)) {
            validationResult.addErrorMessage("Пароль должен содержать хотя бы 1 цифру");
        }
        if (!hasLowercase(password)) {
            validationResult.addErrorMessage("Пароль должен содержать хотя бы 1 символ в нижнем " +
                "регистре");
        }
        if (!hasUppercase(password)) {
            validationResult.addErrorMessage(
                "Пароль должен содержать хотя бы 1 символ в верхнем регистре");
        }
        if (password.equals(userName)) {
            validationResult.addErrorMessage("Пароль не должен совпадать с именем пользователя");
        }
        if (hasSpacesOrQuotes(password)) {
            validationResult.addErrorMessage("Пароль не должен содержать пробелов или кавычек");
        }

        return validationResult;
    }

    /**
     * Проверка валидности пароля
     *
     * @param password пароль
     * @param userName имя пользователя
     * @return возвращает true, если пароль отвечает всем требованиям
     */
    public static boolean isValidPassword(String password, String userName) {
        return validatePassword(password, userName).isValid();
    }

    private static boolean hasDigits(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isDigit(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasLowercase(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isLowerCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasUppercase(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isUpperCase(symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasSpacesOrQuotes(String text) {
        for (char symbol : text.toCharArray()) {
            if (Character.isSpaceChar(symbol)
                || symbol == '\t' || symbol == '"') {
                return true;
            }
        }
        return false;
    }

    /**
     * The type Validation result.
     */
    static class ValidationResult {
        private boolean isValid;
        private final List<String> errorMessages;

        /**
         * Instantiates a new Validation result.
         */
        public ValidationResult() {
            this.isValid = true;
            this.errorMessages = new ArrayList<>();
        }

        /**
         * Instantiates a new Validation result.
         *
         * @param isValid       the is valid
         * @param errorMessages the error messages
         */
        public ValidationResult(boolean isValid, List<String> errorMessages) {
            this.isValid = isValid;
            this.errorMessages = errorMessages;
        }

        /**
         * Add error message.
         *
         * @param errorMessage the error message
         */
        public void addErrorMessage(String errorMessage) {
            if (isValid) {
                isValid = false;
            }
            errorMessages.add(errorMessage);
        }

        /**
         * Is valid boolean.
         *
         * @return the boolean
         */
        public boolean isValid() {
            return isValid;
        }

        /**
         * Gets error messages.
         *
         * @return the error messages
         */
        public List<String> getErrorMessages() {
            return errorMessages;
        }

        @Override
        public final boolean equals(Object o) {
            if (!(o instanceof PasswordValidator.ValidationResult result))
                return false;

            return isValid == result.isValid() && errorMessages.equals(result.getErrorMessages());
        }

        @Override
        public int hashCode() {
            int result = Boolean.hashCode(isValid);
            result = 31 * result + errorMessages.hashCode();
            return result;
        }
    }
}