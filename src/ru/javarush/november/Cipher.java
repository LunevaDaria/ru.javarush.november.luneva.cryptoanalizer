package ru.javarush.november;

import java.io.*;
import java.util.*;

public class Cipher {

    public static String fileName = null;
    public static HashMap<Integer, Character> alphabet;
    public static int alphabetSize;
    public static char symbol;
    public int currentNumberOfSymbol;
    private Integer numberOfEncryptedSymbol;
    public Integer numberOfDecryptedSymbol;
    private char encryptedSymbol;
    public char decryptedSymbol;


    public static HashMap<Integer, Character> downloadAlphabet() {

        Scanner scanner = new Scanner(System.in);
        alphabet = new HashMap<Integer, Character>();
        System.out.println("Введите адрес файла с алфавитом:");

        try (FileReader file = new FileReader(scanner.nextLine())) {
            Integer key = 1;
            while (file.ready()) {
                char symbol = (char) file.read();
                alphabet.put(key, symbol);
                key++;
            }
            System.out.println("Алфавит загружен.");
            alphabetSize = alphabet.size();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Проверьте адрес файла.");
            downloadAlphabet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alphabet;
    }

    public void encrypt() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите адрес файла с текстом для шифрования.");
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
                        encryptSymbol();
                        bufferedWriter.write(encryptedSymbol);
                    }
                }
            }
            System.out.println("Файл записан.");
        } catch (FileNotFoundException e) {
            System.out.println("Введите адрес файла с текстом для шифрования.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String chooseFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите адрес файла с текстом для расшифровки");
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
                        dencryptSymbol();
                        bufferedWriter.write(decryptedSymbol);
                    }
                }
            }
            System.out.println("Файл записан.");
        } catch (FileNotFoundException e) {
            System.out.println("Вы неверно ввели адрес файла с текстом для расшифровки");
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
        numberOfDecryptedSymbol = currentNumberOfSymbol - CipherKey.cipherKey;
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
        numberOfEncryptedSymbol = currentNumberOfSymbol + CipherKey.cipherKey;
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
}
