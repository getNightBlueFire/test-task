package service.console.app;

public class CreditCard {
    String number;
    String ownerFirstName;
    String ownerSecondName;
    private int cvc;
    int year;
    int month;
    private int pin;

    int money;
    Bank bank;

    public int getCvc() {
        return cvc;
    }
    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public int getPin() {
        return pin;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }
    public CreditCard(String ownerFirstName, String ownerSecondName, Bank bank) {
        this.ownerFirstName = ownerFirstName;
        this.ownerSecondName = ownerSecondName;
        this.bank = bank;
    }

    public void getCreditCardInformation() {
        System.out.println("Credit card number: " + this.number + ";\nCredit card owner: " + this.ownerFirstName +
                " " + this.ownerSecondName + ";\nBank: " + this.bank.name +
                ";\nCredit card expiry date: " + this.month + "/" + this.year + ";\nAmount of money:" + this.money + ".");
    }

}
