package ru.teamscore.ui;

import ru.teamscore.core.AlreadyExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckDuplicates {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите текст. Значения разделяйте нажатием enter");

        List<String> values = new ArrayList<>();

        try {
            while (true) {
                String value = sc.nextLine();
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
