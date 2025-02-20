package org.ivan.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {
    public static int inputInt(Scanner scanner) {
        while (true) {
            try {
                int result = scanner.nextInt();
                return result;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат ввода, попробуйте снова");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static String inputString(Scanner scanner) {
        return inputString(scanner, false);
    }

    public static String inputString(Scanner scanner, boolean allowEmpty) {
        while (true) {
            try {
                String result = scanner.nextLine().trim();
                if (allowEmpty || !result.isEmpty()) return result;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат ввода, попробуйте снова");
            }
        }
    }
}
