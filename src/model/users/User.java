package model.users;

import model.accounts.*;
import model.currency.Money;
import model.currency.USD;
import util.ID;
import util.Reader;

public class User {
    private final ID id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String birth;
    private Account saving;
    private Account checking;
    private Account loan;
    private Account securities;

    public User(String id, String username, String firstName, String lastName, String password, String birth) {
        this.id = new ID(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birth = birth;
    }

    public User(String username, String firstName, String lastName, String password, String birth) {
        id = new ID();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birth = birth;
        this.saving = null;
        this.checking = null;
        this.loan = null;
        this.securities = null;
    }

    public Account getSaving() {
        Account a = null;
        if (hasSaving()) {
            for (String s : Reader.L5) {
                String[] ss = s.split(" ", 0);
                if (ss[1].equals(id.toString())) {
                    a = new SavingsAccount(ss[0], id, USD.getInstance(), new Money(Double.parseDouble(ss[3])));
                    setSaving(a);
                }
            }
        }
        return a;
    }

    public Account getChecking() {
        Account a = null;
        if (hasChecking()) {
            for (String s : Reader.L4) {
                String[] ss = s.split(" ", 0);
                if (ss[1].equals(id.toString())) {
                    a = new CheckingAccount(ss[0], id, USD.getInstance(), new Money(Double.parseDouble(ss[3])));
                    setChecking(a);
                }
            }
        }
        return a;
    }

    public Account getLoan() {
        Account a = null;
        if (hasLoan()) {
            for (String s : Reader.L6) {
                String[] ss = s.split(" ", 0);
                if (ss[1].equals(id.toString())) {
                    a = new LoanAccount(ss[0], id, USD.getInstance(), new Money(Double.parseDouble(ss[3])), new Money(Double.parseDouble(ss[10])), new Money(Double.parseDouble(ss[11])));
                    setLoan(a);
                }
            }
        }
        return a;
    }

    public SecuritiesAccount getSecurities() {
        SecuritiesAccount a = null;
        if (hasSecurities()) {
            for (String s : Reader.L3) {
                String[] ss = s.split(" ", 0);
                if (ss[1].equals(id.toString())) {
                    a = new SecuritiesAccount(ss[0], id, USD.getInstance(), new Money(Double.parseDouble(ss[3])));
                    setSecurities(a);
                }
            }
        }
        return a;
    }
    public boolean hasSecurities() {
        //read fron databases to check whether there is a existing account
        for (String s : Reader.L3) {
            if (s.split(" ", 0)[1].equals(id.toString())) {
                return true;
            }
        }
        return false;
    }
    public boolean hasChecking() {
        //read fron databases to check whether there is a existing account
        for (String s : Reader.L4) {
            if (s.split(" ", 0)[1].equals(id.toString())) {
                return true;
            }
        }
        return false;
    }
    public boolean hasSaving() {
        //read fron databases to check whether there is a existing account
        for (String s : Reader.L5) {
            if (s.split(" ", 0)[1].equals(id.toString())) {
                return true;
            }
        }
        return false;
    }
    public boolean hasLoan() {
        //read fron databases to check whether there is a existing account
        for (String s : Reader.L6) {
            if (s.split(" ", 0)[1].equals(id.toString())) {
                return true;
            }
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getBirth() {
        return birth;
    }

    public void setSaving(Account saving) {
        this.saving = saving;
    }

    public void setChecking(Account checking) {
        this.checking = checking;
    }

    public void setLoan(Account loan) {
        this.loan = loan;
    }

    public void setSecurities(Account securities) {
        this.securities = securities;
    }

    public ID getId() {
        return id;
    }
    public String toString() {
        return id + " " + username + " " + firstName + " " + lastName + " " + password + " " + birth;
    }
    public void updateBalance(double offset) {

    }
}
