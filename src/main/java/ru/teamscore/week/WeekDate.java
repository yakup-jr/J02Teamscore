package ru.teamscore.week;

import java.time.LocalDate;
import java.util.List;

public interface WeekDate {

    List<LocalDate> getMondayAndSundayByDate(int year, int week);

}
