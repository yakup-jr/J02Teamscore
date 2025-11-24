package ru.teamscore.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class WeekUiFormatterAndValidator {

    private WeekUiFormatterAndValidator() {
    }

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
                "Номер недели не может быть больше, чем их " + "количество в году");
        }
    }

    public static String formatOutput(List<LocalDate> dates) {
        return String.format("Пн: %s%n", dates.get(0)) + String.format("Вс: %s", dates.get(1));
    }

}
