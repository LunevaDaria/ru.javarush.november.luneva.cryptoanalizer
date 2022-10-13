package ru.javarush.november;

import java.io.*;
import java.util.*;

public class Cipher {

    public static String fileName = null;
    public static HashMap<Integer, Character> alphabet;
    public static int alphabetSize;
    public static char symbol;
    public int currentNumberOfSymbol;

//    private Integer numberOfEncryptedSymbol;
//    private Integer numberOfDecryptedSymbol;
//    private char decryptedSymbol;
//    private char encryptedSymbol;


    public static HashMap<Integer, Character> downloadAlphabet() {

        Scanner scanner = new Scanner(System.in);
        alphabet = new HashMap<Integer, Character>();
        print("Введите адрес файла с алфавитом:");

        try (FileReader file = new FileReader(scanner.nextLine())) {
            Integer key = 1;
            while (file.ready()) {
                char symbol = (char) file.read();
                alphabet.put(key, symbol);
                key++;
            }
            print("Алфавит загружен.");
            alphabetSize = alphabet.size();
        } catch (FileNotFoundException e) {
            print("Файл не найден. Проверьте адрес файла.");
            downloadAlphabet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alphabet;
    }

    public void encrypt() {

        Scanner scanner = new Scanner(System.in);
        print("Введите адрес файла с текстом для шифрования.");
        fileName = scanner.nextLine();

        try (FileReader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            try (FileWriter encryptedFile = new FileWriter("encryptedFile.txt");
                 BufferedWriter bufferedWriter = new BufferedWriter(encryptedFile)) {

                while (bufferedReader.ready()) {
                    symbol = (char) bufferedReader.read();
                    getNumberOfSymbol();
                    if (currentNumberOfSymbol == 0) {
                        bufferedWriter.write(symbol);
                    } else {
                        bufferedWriter.write(encryptSymbol());
                    }
                }
            }
            print("Файл записан.");
        } catch (FileNotFoundException e) {
            print("Введите адрес файла с текстом для шифрования.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String chooseFile() {
        Scanner scanner = new Scanner(System.in);
        print("Введите адрес файла с текстом для расшифровки");
        fileName = scanner.nextLine();
        return fileName;
    }

    public void dencrypt() {

        try (FileReader reader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            try (FileWriter decryptedFile = new FileWriter("decryptedFile.txt");
                 BufferedWriter bufferedWriter = new BufferedWriter(decryptedFile)) {

                while (bufferedReader.ready()) {
                    symbol = (char) bufferedReader.read();
                    getNumberOfSymbol();
                    if (currentNumberOfSymbol == 0) {
                        bufferedWriter.write(symbol);
                    } else {
                        bufferedWriter.write(dencryptSymbol());
                    }
                }
            }
            print("Файл записан.");
        } catch (FileNotFoundException e) {
            print("Вы неверно ввели адрес файла с текстом для расшифровки");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getNumberOfSymbol() {
        currentNumberOfSymbol = 0;
        for (Map.Entry<Integer, Character> pair : alphabet.entrySet()) {
            if (pair.getValue().equals(symbol)) {
                currentNumberOfSymbol = pair.getKey();
            }
        }
        return currentNumberOfSymbol;
    }

    public char dencryptSymbol() {
        char decryptedSymbol = '\u0000';
        Integer numberOfDecryptedSymbol = currentNumberOfSymbol - CipherKey.cipherKey;
        if (numberOfDecryptedSymbol < 0) {
            numberOfDecryptedSymbol = alphabetSize - (CipherKey.cipherKey - currentNumberOfSymbol);
        }
        for (Integer key : alphabet.keySet()) {
            if (numberOfDecryptedSymbol.equals(key)) {
                decryptedSymbol = alphabet.get(numberOfDecryptedSymbol);
            }
        }
        return decryptedSymbol;
    }

    public char encryptSymbol() {
        char encryptedSymbol = '\u0000';
        Integer numberOfEncryptedSymbol = currentNumberOfSymbol + CipherKey.cipherKey;
        if (numberOfEncryptedSymbol > Cipher.alphabetSize) {
            numberOfEncryptedSymbol = (currentNumberOfSymbol + CipherKey.cipherKey) % alphabetSize;
        }
        for (Integer key : alphabet.keySet()) {
            if (numberOfEncryptedSymbol.equals(key)) {
                encryptedSymbol = alphabet.get(numberOfEncryptedSymbol);
            }
        }
        return encryptedSymbol;
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
