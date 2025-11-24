package ru.teamscore.core;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;

public record MonthInfo(LocalDate time) {

    public MonthInfo() {
        this(LocalDate.now());
    }

    public String getMonthName() {
        return switch (time.getMonth()) {
            case JANUARY -> "Январь";
            case FEBRUARY -> "Февраль";
            case MARCH -> "Март";
            case APRIL -> "Апрель";
            case MAY -> "Май";
            case JUNE -> "Июнь";
            case JULY -> "Июль";
            case AUGUST -> "Август";
            case SEPTEMBER -> "Сентябрь";
            case OCTOBER -> "Октябрь";
            case NOVEMBER -> "Ноябрь";
            case DECEMBER -> "Декабрь";
        };
    }

    public int getMonthNumber() {
        return time.getMonthValue();
    }

    public String getFirstDayNameOfMonth() {
        DayOfWeek firstDayOfMonth =
            LocalDateTime.of(time.getYear(), time.getMonth(), 1, 0, 0).getDayOfWeek();

        return switch (firstDayOfMonth) {
            case MONDAY -> "пн";
            case TUESDAY -> "вт";
            case WEDNESDAY -> "ср";
            case THURSDAY -> "чт";
            case FRIDAY -> "пт";
            case SATURDAY -> "сб";
            case SUNDAY -> "вс";
        };
    }

    public LocalDate getLastDayOfMonth() {
        return LocalDate.of(time.getYear(), time.getMonth(), time.lengthOfMonth());
    }

    public int getDaysInMonth() {
        return time.lengthOfMonth();
    }

    public String getQuartalWithYear() {
        int quarter = time.get(IsoFields.QUARTER_OF_YEAR);
        return String.format("%s Q%s", time.getYear(), quarter);
    }
}
