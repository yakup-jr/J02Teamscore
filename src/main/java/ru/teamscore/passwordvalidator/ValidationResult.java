package ru.teamscore.passwordvalidator;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private boolean isValid;
    private final List<String> errorMessages;

    public ValidationResult() {
        this.isValid = true;
        this.errorMessages = new ArrayList<>();
    }

    public ValidationResult(boolean isValid, List<String> errorMessages) {
        this.isValid = isValid;
        this.errorMessages = errorMessages;
    }

    public void addErrorMessage(String errorMessage) {
        if (isValid) {
            isValid = false;
        }
        errorMessages.add(errorMessage);
    }

    public boolean isValid() {
        return isValid;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof ValidationResult result)) return false;

        return isValid == result.isValid && errorMessages.equals(result.errorMessages);
    }

    @Override
    public int hashCode() {
        int result = Boolean.hashCode(isValid);
        result = 31 * result + errorMessages.hashCode();
        return result;
    }
}
