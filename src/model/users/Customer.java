package model.users;

public class Customer extends User{
    private final String cardNumber;

    public Customer(String id, String username, String firstName, String lastName, String password, String birth, String cardNumber) {
        super(id, username, firstName, lastName, password, birth);
        this.cardNumber = cardNumber;
    }

    public Customer(String username, String firstName, String lastName, String password, String birth, String cardNumber) {
        super(username, firstName, lastName, password, birth);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public String toString() {
        return super.toString() + " " + cardNumber;
    }
}
