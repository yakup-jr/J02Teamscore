package ru.teamscore.core;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class WeekIsoImpl implements Week {
    @Override
    public List<LocalDate> getWeekDates(int year, int week) {
        LocalDate firstDayOfFirstWeekOfYear = getFirstDayOfFirstWeekOfYear(year);
        LocalDate firstDayOfWeek = firstDayOfFirstWeekOfYear.plusWeeks((long) week - 1);
        LocalDate lastDayOfWeek = firstDayOfWeek.plusDays(6);

        if (firstDayOfWeek.getYear() > year) {
            throw new IllegalArgumentException("Номер недели не может быть больше чем недель в " +
                "году");
        }

        return List.of(firstDayOfWeek, lastDayOfWeek);
    }

    private static LocalDate getFirstDayOfFirstWeekOfYear(int year) {
        return LocalDate.of(year, 1, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
    }
}
