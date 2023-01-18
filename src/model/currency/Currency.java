package model.currency;

public abstract class Currency {

    protected String name;
    protected  CurrencyType type;
    protected double amount;
    protected double exchangeRate2CNY;
    protected double exchangeRate2USD;
    protected double exchangeRate2INR;


    public Currency() {
        this.type = CurrencyType.USD;
    }

    public CurrencyType getType() {
        return type;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean setAmountAnyway(double amt) {
        amount = Double.parseDouble(String.format("%.2f", amt));
        return true;
    }


    public boolean setAmount(double amt) {
        if (amt < 0) return false;
        return setAmountAnyway(amt);
    }

    public double getAmount() {
        return amount;
    }

    public void setExchangeRate2CNY(double rate) {
        this.exchangeRate2CNY = rate;
    }

    public void setExchangeRate2USD(double rate) {
        this.exchangeRate2USD = rate;
    }

    public void setExchangeRate2INR(double rate) {
        this.exchangeRate2INR = rate;
    }

    public double getExchangeRate2CNY() {
        return exchangeRate2CNY;
    }

    public double getExchangeRate2USD() {
        return exchangeRate2USD;
    }

    public double getExchangeRate2INR() {
        return exchangeRate2INR;
    }
}
