package model.accounts;

import model.accounts.accountBehavior.IntrestBehavior;
import model.currency.Currency;
import model.currency.Money;
import util.ID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {
    private final ID id;
    private final ID userID;
    private final Currency currency;
    private Money balance;
    private Date date;
    private AccountType type;

    protected IntrestBehavior interestBehavior;


    public Account(ID userID, Currency currency, Money balance, AccountType type) {
        this.id = new ID();
        this.userID = userID;
        this.currency = currency;
        this.balance = balance;
        this.date = new Date();
        this.type = type;
    }

    public Account(String id, ID userID, Currency currency, Money balance, AccountType type) {
        this.id = new ID(id);
        this.userID = userID;
        this.currency = currency;
        this.balance = balance;
        this.type = type;
        this.date = new Date();
    }
    public boolean buyStock(ID userID, String name, int number) throws IOException {
        return true;
    }
    public boolean sellStock(String path, String stockName, int sellCount) throws IOException {
        return true;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public ID getId() {
        return id;
    }

    public ID getUserID() {
        return userID;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money getBalance() {
        return balance;
    }
    public Money getUnpaidLoan() {
        return new Money(0);
    }
    public Money getPaidLoan() {
        return new Money(0);
    }

    public Date getDate() {
        return date;
    }
    public String toString() {
        return id + " " + userID + " " + currency.toString() + " " + balance.toString() + " " + date;
    }
    //date diffMoney afterMoney
    public List<String> print(List<String> input) {
        List<String> list = new ArrayList<>();
        String before = "";
        for (String s : input) {
            String[] ss = s.split(" ", 0);
            if (ss[0].toString().equals(getId().toString())) {
                String diff = list.size() == 0 ? ss[3] : String.valueOf(Double.parseDouble(ss[3]) - Double.parseDouble(before));
                String after = ss[3];
                list.add(ss[4] + " " + ss[5] + " " + ss[6] + " " + ss[7] + " " + ss[8] + " " + ss[9] + " " + diff + " " + after);
                before = ss[3];
            }
        }
        return list;
    }

    public void interest(int mints){
        interestBehavior.interest(mints);
    }
}
