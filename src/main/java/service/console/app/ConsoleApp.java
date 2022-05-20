package service.console.app;

import static service.console.app.DataBaseImitation.tinkoffbank;

public class ConsoleApp {
    public static void main(String[] args) {

        Person dima = new Person("Dima", "Testovich");

        dima.requestCreditCard(tinkoffbank);

        tinkoffbank.getListPersonsInBank();

    }


}
