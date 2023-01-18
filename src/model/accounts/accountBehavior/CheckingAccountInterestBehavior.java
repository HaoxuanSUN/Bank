package model.accounts.accountBehavior;

import model.accounts.CheckingAccount;

public class CheckingAccountInterestBehavior implements IntrestBehavior{
    CheckingAccount account;
    public CheckingAccountInterestBehavior(CheckingAccount account){
        this.account=account;
    }
    public void interest(int mints){

    }
}