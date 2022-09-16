package ru.javarush.november;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CipherKey {

    public static int cipherKey;

    public static int setCipherKey() {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите ключ для шифра. Ключ - это число.");
            int userKey = scanner.nextInt();
            if (userKey > 0 && userKey <= Cipher.alphabetSize) {
                cipherKey = userKey;
            } else if (userKey > Cipher.alphabetSize) {
                cipherKey = userKey % Cipher.alphabetSize;
            } else {
                System.out.println("Ключ не может быть отрицательным числом.");
                setCipherKey();
            }
        } catch (InputMismatchException e) {
            System.out.println("Ключ не может содержать буквы.");
            setCipherKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherKey;
    }
}
