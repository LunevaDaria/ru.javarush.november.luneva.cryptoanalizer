package ru.javarush.november;

import java.util.Scanner;

public class Menu {

    private final String WELCOME = "Что Вы хотите сделать?";
    private final String ENCRYPTION = "1. ШИФРОВАНИЕ";
    private final String DECRYPTION = "2. ДЕШИФРОВКА";
    private final String BRUTEFORCE = "3. BRUTE FORCE";
    private final String EXIT = "4. ВЫХОД";
    private final String CHOOSE_MODE = "Введите номер режима от 1 до 4";
    private final String SELECTED_MODE = "Вы выбрали режим ";

    public void run() {

        print(WELCOME);
        print(ENCRYPTION);
        print(DECRYPTION);
        print(BRUTEFORCE);
        print(EXIT);
        print(CHOOSE_MODE);

        try (Scanner scanner = new Scanner(System.in)) {
            int numberOfMode = scanner.nextInt();

            switch (numberOfMode) {
                case 1:
                    encrypt();
                    break;
                case 2:
                    dencrypt();
                    break;
                case 3:
                    hack();
                    break;
                case 4:
                    print(SELECTED_MODE + EXIT);
                    break;
                default: run();
            }
        } catch (Exception e) {
            print("Вы неправильно ввели номер режима. Перезагрузите программу.");
        }
    }

    private void hack() {
        print(SELECTED_MODE + BRUTEFORCE);
        Cipher.downloadAlphabet();
        BruteForce bruteForce = new BruteForce();
        bruteForce.analyzeCipher(Cipher.chooseFile());
        bruteForce.searchCipherKey();
        Cipher cipher2 = new Cipher();
        cipher2.dencrypt();
    }

    private void dencrypt() {
        print(SELECTED_MODE + DECRYPTION);
        Cipher.downloadAlphabet();
        CipherKey.chooseCipherKey();
        Cipher cipher = new Cipher();
        cipher.chooseFile();
        cipher.dencrypt();
    }

    private void encrypt() {
        print(SELECTED_MODE + ENCRYPTION);
        Cipher.downloadAlphabet();
        CipherKey.chooseCipherKey();
        new Cipher().encrypt();
    }

    public void print(String message) {
        System.out.println(message);
    }
}
