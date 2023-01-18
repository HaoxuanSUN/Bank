package service;

import data.AccountDao;
import data.TransactionDao;
import model.accounts.Account;
import model.transactions.*;
import model.users.User;
import util.Constants;

import java.io.IOException;

public class MakeTransactions {
    private User user;
    private User manager;
    private AccountDao accountDao = new AccountDao();
    private TransactionDao transactionDao = new TransactionDao();
    public MakeTransactions(User user, User manager) {
        this.user = user;
        this.manager = manager;
    }
    public void deposit(Account a, double amount) throws IOException {
        Transaction deposit = new Deposit(user.getId(), null, a.getId(), a.getCurrency(), amount, TransactionType.DEPOSIT);
        transactionDao.save(deposit);
        a.getBalance().addMoney(amount);
        accountDao.save(a);
    }
    public void withdraw(Account a, double amount) throws IOException {
        if (amount <= a.getBalance().getValue()) {
            Transaction withdraw = new Withdraw(user.getId(), null, a.getId(), a.getCurrency(), amount, TransactionType.WITHDRAW);
            transactionDao.save(withdraw);
            a.getBalance().removeMoney(amount);
            manager.updateBalance(Constants.Fee);
            accountDao.save(a);
        }
    }
    public void payLoan(Account a, double amount) throws IOException {
        double d = a.getUnpaidLoan().getValue();
        if (d > 0) {
            Transaction payLoan = new PayLoan(user.getId(), null, a.getId(), a.getCurrency(), Math.min(d, amount), TransactionType.PAYLOAN);
            transactionDao.save(payLoan);
            a.getUnpaidLoan().removeMoney(Math.min(d, amount));
            a.getPaidLoan().addMoney(Math.min(d, amount));
            accountDao.save(a);
        }
    }
    public void transfer(Account fromA, Account toB, double amount) throws IOException {
        double d = fromA.getBalance().getValue();
        if (d > amount) {
            Transaction transfer = new Transfer(user.getId(), fromA.getId(), toB.getId(), fromA.getCurrency(), amount, TransactionType.TRANSFER);
            transactionDao.save(transfer);
            fromA.getBalance().removeMoney(amount);
            toB.getBalance().addMoney(amount);
            accountDao.save(fromA);
            accountDao.save(toB);
        }
    }
}
