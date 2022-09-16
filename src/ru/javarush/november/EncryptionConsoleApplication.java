package ru.javarush.november;


public class EncryptionConsoleApplication {
    private static final String HELLO = "Добро пожаловать в криптоанализатор!";

    public static void main(String[] args) {

        System.out.println(HELLO);
        new Menu().openMenu();
    }
}
