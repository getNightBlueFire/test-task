package service.console.app;

import static service.console.app.DataBaseImitation.tinkoffbank;

public class ConsoleApp {
    public static void main(String[] args) {

        Person dima = new Person("Dima", "Testovich");

        dima.requestCreditCard(tinkoffbank);
        String number = dima.creditCards[0].number;


        //пользователь положил на карту 1000
        dima.addMoneyToCart(number, 1000);
        //пользователь потратил 999
        dima.payMoneyFromCart(number,999);

        dima.closeCreditCard(tinkoffbank, number);

        tinkoffbank.getListPersonsInBank();

    }


}
