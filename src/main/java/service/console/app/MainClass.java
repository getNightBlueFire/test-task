package service.console.app;

import java.util.Arrays;

import static service.console.app.DataBaseImitation.belarusbank;
import static service.console.app.DataBaseImitation.priorbank;

public class MainClass {
    public static void main(String[] args) {
        Person guy = new Person("Ivan", "Ivanov");
        CreditCard priorbankCreditCard = guy.requestCreditCard(priorbank);
        CreditCard belarusbankCreditCard = guy.requestCreditCard(belarusbank);

        guy.addMoneyToCard(priorbankCreditCard.number, 1000);
        guy.addMoneyToCard(belarusbankCreditCard.number, 650);
        guy.transferMoneyFromCardToCard(priorbankCreditCard.number, belarusbankCreditCard.number, 100);
        guy.payMoneyFromCard(priorbankCreditCard.number, 50);
        guy.payMoneyFromCard(priorbankCreditCard.number, 150);
        guy.addMoneyToCard(priorbankCreditCard.number, 140);
        guy.transferMoneyFromCardToCard(belarusbankCreditCard.number, priorbankCreditCard.number, 20);
        priorbankCreditCard.bank.createAccountStatement();
    }
}
