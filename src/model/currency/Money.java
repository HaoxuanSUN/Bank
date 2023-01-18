package model.currency;

import util.Constants;

public class Money implements Comparable<Money>{
    private double value;

    public Money(double value) {
        this.value = value;
    }
    public void addMoney(double d) {
        value += d;
    }
    public boolean removeMoney(double d) {
        if (d > value) {
            return false;
        }
        value -= d;
        return true;
    }
    public double getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money other = (Money) o;
        return Double.compare(value, other.getValue()) == 0;
    }
    public double change2USD(CurrencyType t) {
        switch (t) {
            case CNY:
            case INR:
                return value * Constants.currency.get(t);
            case USD:
                return value;
        }
        return 0.0;
    }
    public String toString() {
        return String.valueOf(value);
    }
    public int compareTo(Money m) {
        return (int)(value - m.getValue());
    }
}
