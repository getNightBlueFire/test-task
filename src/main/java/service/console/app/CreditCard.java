package service.console.app;

public class CreditCard {
    String number;
    String ownerFirstName;
    String ownerSecondName;
    int cvc;
    int year;
    int month;
    int pin;

    int money;
    Bank bank;
    String[] OperationsList = new String[10];
    public CreditCard(String ownerFirstName, String ownerSecondName, Bank bank) {
        this.ownerFirstName = ownerFirstName;
        this.ownerSecondName = ownerSecondName;
        this.bank = bank;
    }


}
