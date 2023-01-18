package service;

import data.AccountDao;
import model.accounts.*;
import model.currency.Money;
import model.currency.USD;
import model.users.User;
import util.Constants;

import java.io.IOException;

public class CreateAccount {
    AccountDao accountDao = new AccountDao();
    private User user;
    private User manager;
    public CreateAccount(User user, User manager) {
        this.user = user;
        this.manager = manager;
    }

    public boolean createSavingA(double startMoney) throws IOException {
        if (startMoney > 100.0 && !user.hasSaving()) {
            Account a = new SavingsAccount(user.getId(), USD.getInstance(), new Money(startMoney - Constants.Fee));
            user.setSaving(a);
            accountDao.save(a);
            manager.updateBalance(Constants.Fee);
            return true;
        }
        return false;
    }

    /**
     * request loan
     * @param requestLoan
     * @return
     * @throws IOException
     */
    public boolean createLoanA(double requestLoan) throws IOException {
        if (user.hasSaving() && user.getSaving().getBalance().getValue() >= 1000 && !user.hasLoan()) {
            Account a = new LoanAccount(user.getId(), USD.getInstance(), new Money(requestLoan));
            user.setLoan(a);
            accountDao.save(a);
            manager.updateBalance(Constants.Fee);
            return true;
        }
        return false;
    }
    public boolean createCheckingA(double startMoney) throws IOException {
        if (!user.hasChecking()) {
            Account a = new CheckingAccount(user.getId(), USD.getInstance(), new Money(startMoney - Constants.Fee));
            user.setChecking(a);
            accountDao.save(a);
            manager.updateBalance(Constants.Fee);
            return true;
        }
        return false;
    }
    public boolean createSecuritiesA(double startMoney) throws IOException {
        if (!user.hasSecurities() && startMoney >= 1000 && user.hasSaving() && user.getSaving().getBalance().getValue() >= 5000 && user.getSaving().getBalance().getValue() - startMoney >= 2500) {
            Account a = new SecuritiesAccount(user.getId(), USD.getInstance(), new Money(startMoney - Constants.Fee));
            user.setSecurities(a);
            accountDao.createStockListFile(user.getId().toString());
            accountDao.save(a);
            manager.updateBalance(Constants.Fee);
            return true;
        }
        return false;
    }
}
