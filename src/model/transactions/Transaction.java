package model.transactions;

import data.TransactionDao;
import model.currency.Currency;
import util.ID;

import java.io.IOException;
import java.util.Date;

public class Transaction {
    private final ID id;
    private final ID userID;
    private final ID FromAccountID;
    private final ID ToAccountID;
    private Date date;
    private Currency currency;
    private double Amount;
    private TransactionType type;
    private TransactionDao transactionDao = new TransactionDao();
    public Transaction(ID userID, ID fromAccountID, ID toAccountID, Currency currency, double amount, TransactionType type) {
        this.id = new ID();
        this.userID = userID;
        FromAccountID = fromAccountID;
        ToAccountID = toAccountID;
        this.date = new Date();
        this.currency = currency;
        Amount = amount;
        this.type = type;
    }

    public void excute(Transaction t) throws IOException {
        transactionDao.save(t);
    }
    public String toString() {
        return id + " " + userID + " " + FromAccountID + " " + ToAccountID + " " + date + " " + currency.toString() + " " + Amount + " " + type.toString();
    }
    public ID getId() {
        return id;
    }

    public ID getUserID() {
        return userID;
    }

    public ID getFromAccountID() {
        return FromAccountID;
    }

    public ID getToAccountID() {
        return ToAccountID;
    }

    public Date getDate() {
        return date;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return Amount;
    }
}
