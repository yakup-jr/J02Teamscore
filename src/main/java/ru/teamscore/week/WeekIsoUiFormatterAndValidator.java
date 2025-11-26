package ru.teamscore.week;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * The type Week ui formatter and validator.
 */
public class WeekIsoUiFormatterAndValidator {
    private WeekIsoUiFormatterAndValidator() {
    }

    /**
     * Validate input.
     *
     * @param year the year
     * @param week the week
     */
    public static void validateInput(int year, int week) {
        if (year < 1) {
            throw new IllegalArgumentException("Номер года должен быть положительным");
        }
        if (week < 1) {
            throw new IllegalArgumentException("Номер недели должен быть положительным");
        }

        LocalDate dateConstraints =
            LocalDate.of(year, 1, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))
                .plusWeeks((long) week - 1);
        if (dateConstraints.getYear() > year) {
            throw new IllegalArgumentException(
                "Номер недели не может быть больше, чем их количество в году");
        }
    }

    /**
     * Format output string.
     *
     * @param dates the dates
     * @return the string
     */
    public static String formatOutput(List<LocalDate> dates) {
        return String.format("Пн: %s%n", dates.get(0)) + String.format("Вс: %s", dates.get(1));
    }
}
