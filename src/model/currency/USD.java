package model.currency;

public class USD extends Currency {

    private static USD instance;
    public USD() {

    }
    public String toString() {
        return "USD";
    }
    public CurrencyType getType() {
        return CurrencyType.USD;
    }
    public static Currency getInstance() {
        if(instance == null) {
            instance = new USD();
        }
        return instance;
    }

    USD(double amt) {
        setName("United States Dollar");
        setAmount(amt);

        setExchangeRate2USD(1.0);

    }
}