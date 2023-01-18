package model.accounts.accountBehavior;

import model.accounts.SavingsAccount;
import model.currency.Money;

public class SavingsAccountInterestBehavior implements IntrestBehavior{
    SavingsAccount account;
    public SavingsAccountInterestBehavior(SavingsAccount account){
        this.account=account;
    }

    @Override
    public void interest(int mints) {
        if(Double.compare(account.getBalance().getValue(),5000)>0){
            account.setBalance(
                    new Money(account.getBalance().getValue()*mints/100+account.getBalance().getValue()));
        }
    }
}