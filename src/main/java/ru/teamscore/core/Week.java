package ru.teamscore.core;

import java.time.LocalDate;
import java.util.List;

public interface Week {

    List<LocalDate> getWeekDates(int year, int week);

}
