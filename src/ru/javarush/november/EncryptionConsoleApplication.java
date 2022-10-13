package ru.javarush.november;


public class EncryptionConsoleApplication {
    private static final String HELLO = "Добро пожаловать в криптоанализатор!";

    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.print(HELLO);
        menu.run();
    }
}
