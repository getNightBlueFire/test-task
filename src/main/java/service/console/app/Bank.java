package service.console.app;

import java.time.LocalDate;
import java.util.Random;

/**
 * банк который обслуживает максимум 100 клиентов:(
 */
public class Bank {
    String name;

    public Bank(String name) {
        this.name = name;
    }

    //
    Person[] people = new Person[100];

    /**
     * создает кредитную карту
     *
     * @param person
     * @return
     */
    public CreditCard createCreditCart(Person person) {
        int c = 1;
        for (int i = 0; i < people.length; i++) {
            if (people[i] == null && c == 1) {
                c++;
                people[i] = person;
            }
        }
        CreditCard creditCard = new CreditCard(person.firstName.toUpperCase(), person.secondName.toUpperCase(), this);
        creditCard.month = LocalDate.now().getMonth().getValue();
        creditCard.year = LocalDate.now().getYear() + 3;
        Random random = new Random();
        creditCard.cvc = random.nextInt(899) + 100;
        creditCard.pin = random.nextInt(8999) + 1000;
        creditCard.number = generateCardNumber();

        return creditCard;
    }

    /**
     * утилита которая генегирует рандомный номер карты
     *
     * @return
     */
    private String generateCardNumber() {
        Random r = new Random();
        int a0 = r.nextInt(8999) + 1000;
        int a1 = r.nextInt(8999) + 1000;
        int a2 = r.nextInt(8999) + 1000;
        int a3 = r.nextInt(8999) + 1000;
        return a0 + " " + a1 + " " + a2 + " " + a3;
    }

    /**
     * выводин на консоль список всех клиентов банка и их карты
     */
    public void getListPersonsInBank() {
        System.out.println("Банк:" + this.name);
        for (int i = 0; i < people.length; i++) {
            Person person = people[i];
            if (person == null)
                continue;
            for (int j = 0; j < person.creditCards.length; j++) {
                CreditCard creditCard = person.creditCards[j];
                if (creditCard == null)
                    continue;
                System.out.println("Пользователь " + person.firstName + " " + person.secondName + " держит карту с " +
                        "номером:" + creditCard.number);
            }
            System.out.println();
        }
    }

}
