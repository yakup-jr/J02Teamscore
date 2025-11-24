package ru.teamscore.ui;

import ru.teamscore.core.WeekIsoImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static ru.teamscore.utils.WeekUiFormatterAndValidator.formatOutput;
import static ru.teamscore.utils.WeekUiFormatterAndValidator.validateInput;

public class WeekUi {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.printf("Введите год:%n");
            int year = Integer.parseInt(sc.nextLine());
            System.out.printf("Введите неделю:%n");
            int week = Integer.parseInt(sc.nextLine());

            validateInput(year, week);

            List<LocalDate> weekDates = new WeekIsoImpl().getWeekDates(year, week);

            System.out.println(formatOutput(weekDates));
        } catch (NumberFormatException e) {
            System.out.println("Год и неделя должны быть целыми числами");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
