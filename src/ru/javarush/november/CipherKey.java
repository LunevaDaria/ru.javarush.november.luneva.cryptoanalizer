package ru.javarush.november;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CipherKey {

    public static int cipherKey;

    public static int chooseCipherKey() {

        Scanner scanner = new Scanner(System.in);

        try {
            print("Введите ключ для шифра. Ключ - это число.");
            int userKey = scanner.nextInt();
            if (userKey > 0 && userKey <= Cipher.alphabetSize) {
                cipherKey = userKey;
            } else if (userKey > Cipher.alphabetSize) {
                cipherKey = userKey % Cipher.alphabetSize;
            } else {
                print("Ключ не может быть отрицательным числом.");
                chooseCipherKey();
            }
        } catch (InputMismatchException e) {
            print("Ключ не может содержать буквы.");
            chooseCipherKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherKey;
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
