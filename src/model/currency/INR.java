package model.currency;

public class INR extends Currency {
    private static INR instance;
    public static Currency getInstance() {
        if(instance == null) {
            instance = new INR();
        }
        return instance;
    }
    public String toString() {
        return "INR";
    }
    INR() {
    }

    public CurrencyType getType() {
        return CurrencyType.INR;
    }
    INR(double amt) {
        setName("Indian Rupee");
        setAmount(amt);

        setExchangeRate2INR(1.0);

    }
}