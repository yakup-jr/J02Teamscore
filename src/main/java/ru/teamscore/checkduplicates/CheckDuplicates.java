package ru.teamscore.checkduplicates;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type Check duplicates.
 */
public class CheckDuplicates {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.printf(
            "Введите текст. Значения разделяйте нажатием enter.%n" +
                "Для выхода из программы нажмите esc%n");

        List<String> values = new ArrayList<>();
        boolean isRunning = true;

        try (Scanner sc = new Scanner(System.in)) {

            while (isRunning) {
                String value = sc.nextLine();

                if (value.equalsIgnoreCase("esc")) {
                    isRunning = false;
                    continue;
                }

                if (values.contains(value)) {
                    throw new AlreadyExistsException(value, values.indexOf(value) + 1);
                }

                values.add(value);
            }
        } catch (AlreadyExistsException e) {
            System.out.printf("Вы уже вводили строку %s%nПозиция ранее введенной строки: %d",
                e.getValue(), e.getPosition());
        }
    }
}
