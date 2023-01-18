package model.transactions;

import model.currency.Currency;
import util.ID;

public class StockSell extends Transaction{
    public StockSell(ID userID, ID fromAccountID, ID toAccountID, Currency currency, double amount, TransactionType type) {
        super(userID, fromAccountID, toAccountID, currency, amount, TransactionType.SELLSTOCK);
    }
}
