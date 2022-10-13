package ru.javarush.november;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class BruteForce {

    private char popularChar;
    private final char space = ' ';


    public char analyzeCipher(String fileName) {

        try (FileReader reader = new FileReader(Cipher.fileName);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            HashMap<Character, Long> ciphertextStatistics = new HashMap<>();

            while (bufferedReader.ready()) {
                Cipher.symbol = (char) bufferedReader.read();
                if (ciphertextStatistics.containsKey(Cipher.symbol)) {
                    ciphertextStatistics.put(Cipher.symbol, (ciphertextStatistics.get(Cipher.symbol) + 1));
                } else {
                    long frequency = 1;
                    ciphertextStatistics.put(Cipher.symbol, frequency);
                }
            }
            long maxFrequency = 0L;
            for (Map.Entry<Character, Long> pair : ciphertextStatistics.entrySet()) {
                Long count = pair.getValue();
                if (count > maxFrequency) {
                    maxFrequency = count;
                    popularChar = pair.getKey();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return popularChar;
    }

    public int searchCipherKey() {
        int numberOfSpace = 0;
        int numberOfPopularChar = 0;
        for (Map.Entry<Integer, Character> pair : Cipher.alphabet.entrySet()) {
            if (pair.getValue().equals(space)) {
                numberOfSpace = pair.getKey();
            } else if (pair.getValue().equals(popularChar)) {
                numberOfPopularChar = pair.getKey();
            }
        }
        CipherKey.cipherKey = numberOfPopularChar - numberOfSpace;
        if (CipherKey.cipherKey < 0) {
            CipherKey.cipherKey = Cipher.alphabetSize + CipherKey.cipherKey;
        }
        print("Предполагаемый ключ от шифра = " + CipherKey.cipherKey);
        return CipherKey.cipherKey;
    }
    private void print(String message) {
        System.out.println(message);
    }
}
