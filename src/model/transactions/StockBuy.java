package model.transactions;

import model.currency.Currency;
import util.ID;

public class StockBuy extends Transaction{

    public StockBuy(ID userID, ID fromAccountID, ID toAccountID, Currency currency, double amount, TransactionType type) {
        super(userID, fromAccountID, toAccountID, currency, amount, TransactionType.BUYSTOCK);
    }
}
