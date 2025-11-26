package ru.teamscore.monthinfo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;

/**
 * The type Month info.
 */
public record MonthInfo(LocalDate time) {

    /**
     * Instantiates a new Month info.
     */
    public MonthInfo() {
        this(LocalDate.now());
    }

    /**
     * Gets month name.
     *
     * @return the month name
     */
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

    /**
     * Gets month number.
     *
     * @return the month number
     */
    public int getMonthNumber() {
        return time.getMonthValue();
    }

    /**
     * Gets first day name of month.
     *
     * @return the first day name of month
     */
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

    /**
     * Gets last day of month.
     *
     * @return the last day of month
     */
    public LocalDate getLastDayOfMonth() {
        return LocalDate.of(time.getYear(), time.getMonth(), time.lengthOfMonth());
    }

    /**
     * Gets days in month.
     *
     * @return the days in month
     */
    public int getDaysInMonth() {
        return time.lengthOfMonth();
    }

    /**
     * Gets quartal with year.
     *
     * @return the quartal with year
     */
    public String getQuartalWithYear() {
        int quarter = time.get(IsoFields.QUARTER_OF_YEAR);
        return String.format("%s Q%s", time.getYear(), quarter);
    }
}
