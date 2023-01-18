package model.accounts;

import model.accounts.accountBehavior.LoanAccountInterestBehavior;
import model.currency.Currency;
import model.currency.Money;
import util.Constants;
import util.ID;
import util.Reader;

public class LoanAccount extends Account{
    private Money paidLoan;
    private Money unpaidLoan;
    public LoanAccount(ID userID, Currency currency, Money balance) {
        super(userID, currency, balance, AccountType.LOAN);
        interestBehavior=new LoanAccountInterestBehavior(this);
        this.paidLoan = new Money(0);
        this.unpaidLoan = new Money(balance.getValue() + Constants.Fee);
    }
    //new object from databases
    public LoanAccount(String id, ID userID, Currency currency, Money balance, Money paidLoan, Money unpaidLoan) {
        super(id, userID, currency, balance, AccountType.LOAN);
        this.paidLoan = paidLoan;
        this.unpaidLoan = unpaidLoan;
        interestBehavior=new LoanAccountInterestBehavior(this);
    }

    public Money getPaidLoan() {
        Money a = null;
        for (String s : Reader.L6) {
            String[] ss = s.split(" ", 0);
            if (ss[0].equals(getId().toString())) {
                a = new Money(Double.parseDouble(ss[10]));
                setPaidLoan(a);
            }
        }
        return a;
    }

    public void setPaidLoan(Money paidLoan) {
        this.paidLoan = paidLoan;
    }

    public Money getUnpaidLoan() {
        Money a = null;
        for (String s : Reader.L6) {
            String[] ss = s.split(" ", 0);
            if (ss[0].equals(getId().toString())) {
                a = new Money(Double.parseDouble(ss[11]));
                setUnpaidLoan(a);
            }
        }
        return a;
    }

    public void setUnpaidLoan(Money unpaidLoan) {
        this.unpaidLoan = unpaidLoan;
    }
    public String toString() {
        return super.toString() + " " + paidLoan.toString() + " " + unpaidLoan.toString();
    }
    @Override
    public Money getBalance() {
        Money a = null;
        for (String s : Reader.L6) {
            String[] ss = s.split(" ", 0);
            if (ss[0].equals(getId().toString())) {
                a = new Money(Double.parseDouble(ss[3]));
                setBalance(a);
            }
        }
        return a;
    }
}
