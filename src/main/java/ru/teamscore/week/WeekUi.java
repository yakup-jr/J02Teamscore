package ru.teamscore.week;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static ru.teamscore.week.WeekIsoUiFormatterAndValidator.formatOutput;
import static ru.teamscore.week.WeekIsoUiFormatterAndValidator.validateInput;

/**
 * The type Week ui.
 */
public class WeekUi {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.printf("Введите год:%n");
            int year = Integer.parseInt(sc.nextLine());
            System.out.printf("Введите неделю:%n");
            int week = Integer.parseInt(sc.nextLine());

            validateInput(year, week);

            List<LocalDate> weekDates = new WeekDateIsoImpl().getMondayAndSundayByDate(year, week);

            System.out.println(formatOutput(weekDates));
        } catch (NumberFormatException e) {
            System.out.println("Год и неделя должны быть целыми числами");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
