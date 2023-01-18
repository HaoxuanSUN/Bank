package model.accounts.accountBehavior;

import model.accounts.LoanAccount;
import model.currency.Money;

public class LoanAccountInterestBehavior implements IntrestBehavior{
    LoanAccount account;
    public LoanAccountInterestBehavior(LoanAccount account){
        this.account=account;
    }

    @Override
    public void interest(int mints) {
        account.setUnpaidLoan(new Money(account.getUnpaidLoan().getValue()*mints/100+account.getUnpaidLoan().getValue()));
    }
}