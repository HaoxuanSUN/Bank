package model.currency;

public class CNY extends Currency {
    private static CNY instance;
    public static Currency getInstance() {
        if(instance == null) {
            instance = new CNY();
        }
        return instance;
    }
    CNY() {
    }

    public CurrencyType getType() {
        return CurrencyType.CNY;
    }
    CNY(double amt) {
        setName("Chinese Yuan");
        setAmount(amt);
        setExchangeRate2CNY(1.00);
    }
    public String toString() {
        return "CNY";
    }
}
