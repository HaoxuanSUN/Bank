package model.accounts;

import data.AccountDao;
import data.StockDao;
import data.TransactionDao;
import model.accounts.accountBehavior.SecuritiesAccountInterestBehavior;
import model.currency.Currency;
import model.currency.Money;
import model.stock.Stock;
import model.stock.StockList;
import model.transactions.Transaction;
import model.transactions.TransactionType;
import util.Factory;
import util.ID;
import util.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SecuritiesAccount extends Account {
    private List<StockList> stockList;
    private static BufferedReader reader;
    private AccountDao accountDao = new AccountDao();
    public SecuritiesAccount(ID userID, Currency currency, Money balance) {
        super(userID, currency, balance, AccountType.SECURITIES);

        interestBehavior=new SecuritiesAccountInterestBehavior(this);
        stockList = new ArrayList<>();
    }
    public SecuritiesAccount(String id, ID userID, Currency currency, Money balance) {
        super(id, userID, currency, balance, AccountType.SECURITIES);
        stockList = new ArrayList<>();
        interestBehavior=new SecuritiesAccountInterestBehavior(this);
    }

    public boolean sellStock(String path, String stockName, int sellCount) throws IOException {
        StockDao stockDao = new StockDao();
        boolean stockFound = false;
        boolean canSell = false;
        String newStocktxt = "";
        reader = new BufferedReader(new java.io.FileReader("src/data/StockList/" + path + ".txt"));
        String line = reader.readLine();
        newStocktxt += line + "\n";
        line = reader.readLine();
        newStocktxt += line + "\n";
        line = reader.readLine();
        while (line != null && line.length() != 0) {
            String[] temp = line.split(" ");
            if (temp[2].equalsIgnoreCase(stockName)) {
                stockFound = true;
                int oldStockCount = Integer.parseInt(temp[3]);
                if (oldStockCount < sellCount) {
                    return canSell;
                } else if (oldStockCount == sellCount) {
                    break;
                }
                int newStockCount = oldStockCount - sellCount;
                String newtext = temp[0] + " " + temp[1] + " " + temp[2] + " " + newStockCount + " " + temp[4] +
                        " " + temp[5] + " " + temp[6] + " " + temp[7] + " " + temp[8] + " " + temp[9];
                line = newtext;
            }
            newStocktxt += line + "\n";
            //System.out.println(newStocktxt);
            line = reader.readLine();
        }
        if (!stockFound) {
            return stockFound;
        }
        PrintWriter writer = new PrintWriter("src/data/StockList/" + path + ".txt");
        writer.print("");
        //System.out.println(newStocktxt);
        writer.print(newStocktxt);
        writer.close();
        Reader.update();
        this.getBalance().addMoney(stockDao.getPriceByName(stockName) * sellCount);
        accountDao.save(this);
        return true;
    }
    public boolean buyStock(ID userID, String name, int number) throws IOException {
        TransactionDao transactionDao = new TransactionDao();
        Stock s = Factory.produceStock(name);
        if (s != null && getBalance().change2USD(getCurrency().getType()) >= s.getPrice() * number) {
            StockList list = new StockList(userID, s, number);
            //add to user's stockList
            stockList.add(list);
            //add to user's stockList file
            accountDao.buyStock(userID.toString(), list.toString());
            //add a transaction
            transactionDao.save(new Transaction(userID, null, getId(), s.getCurrency(), s.getPrice() * number, TransactionType.BUYSTOCK));
            this.getBalance().removeMoney(s.getPrice() * number);
            accountDao.save(this);
            return true;
        }
        return false;
    }
    @Override
    public Money getBalance() {
        Money a = null;
        for (String s : Reader.L3) {
            String[] ss = s.split(" ", 0);
            if (ss[0].equals(getId().toString())) {
                a = new Money(Double.parseDouble(ss[3]));
                setBalance(a);
            }
        }
        return a;
    }
    @Override
    public List<String> print(List<String> input) {
        return super.print(Reader.L3);
    }
}
