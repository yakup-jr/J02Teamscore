package ru.teamscore.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeekIsoImplTest {

    @ParameterizedTest
    @MethodSource("provideGetWeekDates")
    void getWeekDates(int year, int week, List<LocalDate> expected) {
        List<LocalDate> weekDates = new WeekIsoImpl().getWeekDates(year, week);
        assertEquals(year, expected.get(0).getYear());
        assertEquals(weekDates.get(0), expected.get(0));
        assertEquals(weekDates.get(1), expected.get(1));
    }

    private static Stream<Arguments> provideGetWeekDates() {
        return Stream.of(
            Arguments.of(2025, 1, List.of(LocalDate.of(2025, 1, 6), LocalDate.of(2025, 1, 12))),
            Arguments.of(2024, 53, List.of(LocalDate.of(2024, 12, 30), LocalDate.of(2025, 1, 5))),
            Arguments.of(2024, 9, List.of(LocalDate.of(2024, 2, 26), LocalDate.of(2024, 3, 3))));
    }

    @Test
    void getWeekDates_WeekMoreThanWeeksInYear() {
        WeekIsoImpl weekIso = new WeekIsoImpl();
        assertThrows(IllegalArgumentException.class, () -> weekIso.getWeekDates(2024, 54));
    }


}
