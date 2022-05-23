package service.console.app;

import static service.console.app.DataBaseImitation.belgazprombank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Person X = new Person("Anastasia" , "Piligina");

        CreditCard blackCard = X.requestCreditCard(belgazprombank);
        belgazprombank.getListPersonsInBank();

        //Operations
        X.addMoneyToCart(blackCard.number , 5000);
        Thread.sleep(1000);
        X.addMoneyToCart(blackCard.number, 10000);
        Thread.sleep(1000);
        X.addMoneyToCart(blackCard.number, 20000);
        Thread.sleep(1000);
        X.payMoneyFromCart(blackCard.number,  4000);
        Thread.sleep(1000);
        X.addMoneyToCart(blackCard.number, 1000000);
        Thread.sleep(1000);
        X.payMoneyFromCart(blackCard.number, 1030000);
        Thread.sleep(1000);
        X.payMoneyFromCart(blackCard.number, 4999);
        Thread.sleep(1000);
        X.closeCreditCard(belgazprombank , blackCard.number);
        Thread.sleep(1000);

        //Request
        System.out.println();
        System.out.println("OPERATIONS REQUEST\n");
        Thread.sleep(1000);
        X.requestOperationsList(blackCard);
    }
}
