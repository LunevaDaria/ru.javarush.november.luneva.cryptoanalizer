package ru.javarush.november;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class BruteForce {

    private char popularChar;
    private static final char space = ' ';
    private int numberOfSpace;
    private int numberOfPopularChar;
    private HashMap<Character, Long> ciphertextStatistics;
    private long frequency = 0;
    private long maxFrequency = 0L;


    public char analyzeCipher() {

        Cipher.chooseFile();

        try (FileReader reader = new FileReader(Cipher.fileName);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            ciphertextStatistics = new HashMap<>();

            while (bufferedReader.ready()) {
                Cipher.symbol = (char) bufferedReader.read();
                if (ciphertextStatistics.containsKey(Cipher.symbol)) {
                    ciphertextStatistics.put(Cipher.symbol, (ciphertextStatistics.get(Cipher.symbol) + 1));
                } else {
                    frequency = 1;
                    ciphertextStatistics.put(Cipher.symbol, frequency);
                }
            }
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
        System.out.println("Предполагаемый ключ от шифра = " + CipherKey.cipherKey);
        return CipherKey.cipherKey;
    }
}
