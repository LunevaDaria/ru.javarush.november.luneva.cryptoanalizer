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

    public void openMenu() {

        Menu menu = new Menu();

        System.out.println(WELCOME);
        System.out.println(ENCRYPTION);
        System.out.println(DECRYPTION);
        System.out.println(BRUTEFORCE);
        System.out.println(EXIT);
        System.out.println(CHOOSE_MODE);

        try (Scanner scanner = new Scanner(System.in)) {
            int numberOfMode = scanner.nextInt();

            switch (numberOfMode) {
                case 1:
                    System.out.println(SELECTED_MODE + ENCRYPTION);
                    Cipher.downloadAlphabet();
                    CipherKey.setCipherKey();
                    new Cipher().encrypt();
                    break;
                case 2:
                    System.out.println(SELECTED_MODE + DECRYPTION);
                    Cipher.downloadAlphabet();
                    CipherKey.setCipherKey();
                    Cipher cipher = new Cipher();
                    cipher.chooseFile();
                    cipher.dencrypt();
                    break;
                case 3:
                    System.out.println(SELECTED_MODE + BRUTEFORCE);
                    Cipher.downloadAlphabet();
                    BruteForce bruteForce = new BruteForce();
                    bruteForce.analyzeCipher();
                    bruteForce.searchCipherKey();
                    Cipher cipher2 = new Cipher();
                    cipher2.dencrypt();
                    break;
                case 4:
                    System.out.println(SELECTED_MODE + EXIT);
                    break;
                default: openMenu();
            }
        } catch (Exception e) {
            System.out.println("Вы неправильно ввели номер режима. Перезагрузите программу.");
        }
    }
}
