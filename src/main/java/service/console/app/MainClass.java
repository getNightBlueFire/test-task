package service.console.app;

import java.util.Arrays;

import static service.console.app.DataBaseImitation.belarusbank;
import static service.console.app.DataBaseImitation.priorbank;

public class MainClass {
    public static void main(String[] args) {
        Person gay = new Person("Ivan", "Ivanov");
        CreditCard priorbankCreditCard = gay.requestCreditCard(priorbank);
        CreditCard belarusbankCreditCard = gay.requestCreditCard(belarusbank);

        gay.addMoneyToCard(priorbankCreditCard.number, 1000);
        gay.addMoneyToCard(belarusbankCreditCard.number, 650);
        gay.transferMoneyFromCardToCard(priorbankCreditCard.number, belarusbankCreditCard.number, 100);
        gay.payMoneyFromCard(priorbankCreditCard.number, 50);
        gay.payMoneyFromCard(priorbankCreditCard.number, 150);
        gay.addMoneyToCard(priorbankCreditCard.number, 140);
        gay.transferMoneyFromCardToCard(belarusbankCreditCard.number, priorbankCreditCard.number, 20);
        priorbankCreditCard.bank.createAccountStatement();
    }
}
