package ru.teamscore.week;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface Week date.
 */
public interface WeekDate {

    /**
     * Gets monday and sunday by date.
     *
     * @param year the year
     * @param week the week
     * @return the monday and sunday by date
     */
    List<LocalDate> getMondayAndSundayByDate(int year, int week);

}
