package ru.javarush.november;


public class EncryptionConsoleApplication {
    public static void main(String[] args) {

        final String HELLO = "Добро пожаловать в криптоанализатор!";
        System.out.println(HELLO);

        new Menu().openMenu();

    }
}
