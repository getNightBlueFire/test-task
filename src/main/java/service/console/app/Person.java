package service.console.app;

/**
 * пользователь банка
 */
public class Person {
    String firstName;
    String secondName;
    CreditCard[] creditCards = new CreditCard[10];

    public Person(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    /**
     * зачисляет на карту с
     *
     * @param number указанную сумму
     * @param money
     */
    public void addMoneyToCard(String number, int money) {
        for (int i = 0; i < creditCards.length; i++) {
            if (creditCards[i] == null)
                continue;
            if (creditCards[i].number.equals(number)) {
                creditCards[i].money += money;
                System.out.println(
                        "На карту **** **** **** " + number.split(" ")[3] + " произошло зачисление. Сумма: " + money);
                System.out.println("Остаток " + creditCards[i].money);
                System.out.println();
            }
        }

    }

    /**
     * списывает на карту с
     *
     * @param number указанную сумму
     * @param money
     */
    public void payMoneyFromCard(String number, int money) {
        for (int i = 0; i < creditCards.length; i++) {
            if (creditCards[i] == null)
                continue;
            if (creditCards[i].number.equals(number) && creditCards[i].money - money >= 0) {
                creditCards[i].money -= money;
                System.out.println(
                        "С карты **** **** **** " + number.split(" ")[3] + " произошло списание. Сумма: " + money);
                System.out.println("Остаток " + creditCards[i].money);
            }
            if(creditCards[i].money - money < 0) {
                System.out.println("Недостаточно средств на карте!");
            }
            }
        System.out.println();
        }


    /**
     * переводит с карты с
     *
     * @param numberCard1 на карту с
     *
     * @param numberCard2 указанную сумму
     *
     * @param money
     *
     */

    public void transferMoneyFromCardToCard(String numberCard1, String numberCard2, int money) {
        for (int i = 0; i < creditCards.length; i++) {
            if (creditCards[i] == null)
                continue;
            if (creditCards[i].number.equals(numberCard1) && creditCards[i].money - money >= 0) {
                creditCards[i].money -= money;
                System.out.println(
                        "С карты **** **** **** " + numberCard1.split(" ")[3] + " произошло списание. Сумма: " + money);
                System.out.println("Остаток " + creditCards[i].money);
                System.out.println();
            }
            if (creditCards[i].money - money < 0) {
                System.out.println("Недостаточно средств на карте!");
                System.out.println();
            }
        }
        for (int i = 0; i < creditCards.length; i++) {
            if (creditCards[i] == null)
                continue;
            if (creditCards[i].number.equals(numberCard2)) {
                creditCards[i].money += money;
                System.out.println(
                        "На карту **** **** **** " + numberCard2.split(" ")[3] + " произошло зачисление. Сумма: " + money);
                System.out.println("Остаток " + creditCards[i].money);
                System.out.println();
            }
        }
    }
    /**
     * запрос от пользователя к
     *
     * @param bank на создание в нем кредитной карты
     */
    public CreditCard requestCreditCard(Bank bank) {
        CreditCard creditCard = bank.createCreditCart(this);
        int c = 1;
        for (int i = 0; i < creditCards.length; i++) {
            if (creditCards[i] == null && c == 1) {
                c++;
                creditCards[i] = creditCard;
            }
        }
        System.out.println("Создана карта с номером " + creditCard.number + " в банке " + bank.name);
        System.out.println();
        return creditCard;
    }

    /**
     * запрос от пользователя на закрытие в банке
     *
     * @param bank   кредитной карты с указанным номером
     * @param number
     */
    public void closeCreditCard(Bank bank, String number) {
        for (int i = 0; i < bank.people.length; i++) {
            if (bank.people[i] == this) {
                Person person = bank.people[i];
                for (int i1 = 0; i1 < person.creditCards.length; i1++) {
                    if (creditCards[i] == null)
                        continue;
                    if (creditCards[i].number == number) {
                        if (creditCards[i].money > 0) {
                            System.out.println("В отделении банка заберите остаток средств:" + creditCards[i].money);
                        }
                        creditCards[i] = null;
                        System.out.println("Ваша карта удалена из базы данных");
                        for (int i2 = 0; i2 < bank.people.length; i2++) {
                            if (bank.people[i2] == this) {
                                bank.people[i2] = null;
                            } else
                                continue;
                        }
                    } else {
                        System.out.println("У вас нет карты с таким номером");
                        return;
                    }
                }
            }
        }
        System.out.println();
    }
}
