package ru.teamscore.week;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeekDateUiFormatterAndValidatorTest {
    @Test
    void validateInput() {
        assertDoesNotThrow(() -> WeekIsoUiFormatterAndValidator.validateInput(2025, 15));
    }

    @ParameterizedTest
    @CsvSource({"-2025, 1", "2025, -1", "2024, 54"})
    void validateInput_throwException(int year, int week) {
        assertThrows(IllegalArgumentException.class,
            () -> WeekIsoUiFormatterAndValidator.validateInput(year, week));
    }

    /*
    Не хватает метода проверки на содержание подстроки. В данном случае использовал бы AssertJ,
    сравнив подстроки дат вывода
    */
    @Test
    void formatOutput() {
        String result = WeekIsoUiFormatterAndValidator.formatOutput(List.of(LocalDate.of(2025, 1, 6),
            LocalDate.of(2025, 1, 12)));
        assertEquals(String.format("Пн: 2025-01-06%nВс: 2025-01-12"), result);
    }
}