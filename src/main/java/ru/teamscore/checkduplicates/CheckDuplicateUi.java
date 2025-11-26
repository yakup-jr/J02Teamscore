package ru.teamscore.checkduplicates;

import java.util.Scanner;

public class CheckDuplicateUi {
    public static void main(String[] args) {
        System.out.printf(
            "Введите текст. Значения разделяйте нажатием enter.%n" +
                "Для выхода из программы введите esc%n");
        try (Scanner sc = new Scanner(System.in)) {
            CheckDuplicate values = new CheckDuplicate();
            boolean isRunning = true;

            while (isRunning) {
                String value = sc.nextLine();

                if ("esc".equalsIgnoreCase(value)) {
                    isRunning = false;
                    continue;
                }

                values.add(value);
            }
        } catch (AlreadyExistsException e) {
            System.out.printf("Вы уже вводили строку %s%nПозиция ранее введенной строки: %d",
                e.getValue(), e.getPosition());
        }
    }
}
