package ru.teamscore.core;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonthInfoTest {
    @ParameterizedTest
    @CsvSource({"1, Январь", "7, Июль", "12, Декабрь"})
    void getMonthName(int month, String expectedName) {
        MonthInfo info = new MonthInfo(LocalDate.of(2025, month, 10));

        assertEquals(expectedName, info.getMonthName());
        assertEquals(month, info.getMonthNumber());
    }

    @ParameterizedTest
    @MethodSource("provideDatesForGetFirstDayNameOfMonth")
    void getFirstDayOfMonth(LocalDate date, String expectedDayName) {
        MonthInfo info = new MonthInfo(date);
        assertEquals(expectedDayName, info.getFirstDayNameOfMonth());
    }

    private static Stream<Arguments> provideDatesForGetFirstDayNameOfMonth() {
        return Stream.of(
            Arguments.of(LocalDate.of(2025, 9, 3), "пн"),
            Arguments.of(LocalDate.of(2025, 10, 1), "ср"),
            Arguments.of(LocalDate.of(2024, 2, 23), "чт"),
            Arguments.of(LocalDate.of(2025, 6, 23), "вс")
        );
    }

    @Nested
    class getLastDayOfMonth {
        @Test
        void getLastDayOfMonth_whenNotLeapYear() {
            MonthInfo info = new MonthInfo(LocalDate.of(2025, 2, 10));

            assertEquals(28, info.getDaysInMonth());
            assertEquals(LocalDate.of(2025, 2, 28), info.getLastDayOfMonth());
        }

        @Test
        void getLastDayOfMonth_whenLeapYear() {
            MonthInfo info = new MonthInfo(LocalDate.of(2024, 2, 10));

            assertEquals(29, info.getDaysInMonth());
            assertEquals(LocalDate.of(2024, 2, 29), info.getLastDayOfMonth());
        }
    }

    @Nested
    class getDaysInMonth {
        @Test
        void getDaysInMonth_when30() {
            MonthInfo jan = new MonthInfo(LocalDate.of(2025, 1, 1));
            assertEquals(31, jan.getDaysInMonth());
            assertEquals(LocalDate.of(2025, 1, 31), jan.getLastDayOfMonth());
        }

        @Test
        void getDaysInMonth_when31() {
            MonthInfo apr = new MonthInfo(LocalDate.of(2025, 4, 1));
            assertEquals(30, apr.getDaysInMonth());
            assertEquals(LocalDate.of(2025, 4, 30), apr.getLastDayOfMonth());
        }

        @Test
        void getDayInMonth_whenFeb() {
            MonthInfo feb = new MonthInfo(LocalDate.of(2025, 2, 1));
            assertEquals(28, feb.getDaysInMonth());
            assertEquals(LocalDate.of(2025, 2, 28), feb.getLastDayOfMonth());
        }

        @Test
        void getDayInMonth_whenFebLeap() {
            MonthInfo apr = new MonthInfo(LocalDate.of(2024, 2, 1));
            assertEquals(29, apr.getDaysInMonth());
            assertEquals(LocalDate.of(2024, 2, 29), apr.getLastDayOfMonth());
        }
    }

    @ParameterizedTest
    @CsvSource({"2025-01-01, 2025 Q1", "2025-03-31, 2025 Q1",
        "2024-04-01, 2024 Q2", "2024-06-30, 2024 Q2",
        "2025-07-01, 2025 Q3", "2025-09-30, 2025 Q3",
        "2024-10-01, 2024 Q4", "2024-12-31, 2024 Q4"
    })
    void testGetQuartalWithYear(LocalDate date, String expectedOutput) {
        MonthInfo info = new MonthInfo(date);
        assertEquals(expectedOutput, info.getQuartalWithYear());
    }
}
