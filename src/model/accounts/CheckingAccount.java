package model.accounts;

import model.accounts.accountBehavior.CheckingAccountInterestBehavior;
import model.currency.Currency;
import model.currency.Money;
import util.ID;
import util.Reader;

import java.util.List;

public class CheckingAccount extends Account {


    public CheckingAccount(ID userID, Currency currency, Money balance) {
        super(userID, currency, balance, AccountType.CHECKING);
        interestBehavior=new CheckingAccountInterestBehavior(this);
    }
    public CheckingAccount(String id, ID userID, Currency currency, Money balance) {
        super(id, userID, currency, balance, AccountType.CHECKING);
        interestBehavior=new CheckingAccountInterestBehavior(this);
    }
    @Override
    public Money getBalance() {
        Money a = null;
        for (String s : Reader.L4) {
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
        return super.print(Reader.L4);
    }
}
