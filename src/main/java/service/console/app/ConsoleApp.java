package service.console.app;

import static service.console.app.DataBaseImitation.tinkoffbank;
import static service.console.app.DataBaseImitation.belarusbank;
import static service.console.app.DataBaseImitation.belgazprombank;
import static service.console.app.DataBaseImitation.priorbank;


public class ConsoleApp {
    public static void main(String[] args) {

        Person dima = new Person("Dima", "Testovich");

        dima.requestCreditCard(tinkoffbank);

        tinkoffbank.getListPersonsInBank();


        String number = dima.creditCards[0].number;


        //пользователь положил на карту 1000
        dima.addMoneyToCart(number, 1000);
        //пользователь потратил 999
        dima.payMoneyFromCart(number,999);

        dima.closeCreditCard(tinkoffbank, number);

        tinkoffbank.getListPersonsInBank();
        belarusbank.getListPersonsInBank();
        priorbank.getListPersonsInBank();
        belgazprombank.getListPersonsInBank();
    }


}
