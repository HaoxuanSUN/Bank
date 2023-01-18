package util;

import model.accounts.Account;
import model.accounts.AccountType;
import model.accounts.LoanAccount;
import model.accounts.SavingsAccount;
import model.currency.Currency;
import model.currency.Money;
import model.currency.USD;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Interest {
    Account account;
    public Interest() throws IOException, ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date now = new Date();

        if(Reader.L7.size()!=0){
            Date date = formatter.parse(Reader.L7.get(Reader.L7.size()-1));
            long duration  = now.getTime() - date.getTime();
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);

            callingInterestFunctionsForLoan((int) diffInMinutes);
            callingInterestFunctionsForSavings((int) diffInMinutes);

        }

        //adding log of time
        Writer.addLine(dateFormat.format(now),"src/data/log.txt");

    }

    //adding interest to the loan accounts
    void callingInterestFunctionsForLoan(int mints){
        for(int i=0 ;i<Reader.L6.size();i++){
            account=new LoanAccount(Reader.L6.get(i).split(" ")[0],
                    new ID(Reader.L6.get(i).split(" ")[1]),
                    new USD(),
                    new Money(Double.parseDouble(Reader.L6.get(i).split(" ")[3])),
                    new Money(Double.parseDouble(Reader.L6.get(i).split(" ")[10])),
                    new Money(Double.parseDouble(Reader.L6.get(i).split(" ")[11])));
            account.interest(mints);
        }
    }

    //adding interest to savings accounts
    void callingInterestFunctionsForSavings(int mints){
        for(int i=0 ;i<Reader.L5.size();i++){
            account=new SavingsAccount(Reader.L5.get(i).split(" ")[0],
                    new ID(Reader.L5.get(i).split(" ")[1]),
                    new USD(),
                    new Money(Double.parseDouble(Reader.L5.get(i).split(" ")[3])));
            account.interest(mints);
        }
    }
}
