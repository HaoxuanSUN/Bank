package model.stock;

import model.currency.Currency;
import model.currency.USD;
import util.ID;

public class Stock {
    private final ID id;
    private String name;
    private double price;
    private Currency currency;
    private String company;

    public Stock(String name, double price, Currency currency, String company) {
        this.id = new ID();
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.company = company;
    }

    public Stock(String name, double price) {
        this.id = new ID();
        this.name = name;
        this.price = price;
        this.currency = USD.getInstance();
        this.company = null;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

    public ID getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }
}
