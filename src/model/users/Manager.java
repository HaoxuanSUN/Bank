package model.users;

import data.UserDao;
import util.Reader;
import util.Writer;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager extends User{

    public Manager(String username, String firstName, String lastName, String password, String birth) {
        super(username, firstName, lastName, password, birth);
    }
    public Map<String, String> getWhoOweMeMost () {
        UserDao userDao = new UserDao();
        Map<String, String> map = helper();
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> m : map.entrySet()) {
            result.put(userDao.getCustomerByID(m.getKey()), m.getValue());
        }
        return result;
    }
    // get the most own me customer id and unpaid loan value
    // return [userid, unpaid]
    public Map<String, String> helper() {
        Map<String, String> map = new HashMap<>();
        List<String> loanAccountText = Reader.getLine("src/data/LoanAccount.txt");
        for (String s : loanAccountText) {
            String[] ss = s.split(" ", 0);
            if (ss[11].equals("0.0")) {
                map.remove(ss[1]);
            } else {
                map.put(ss[1], ss[11]);
            }
        }
        return map;
    }

    // return manager's balance in double
    public double getBalanceDouble() {
        return Reader.getManagerBalance();
    }

    // return manager's balance in String
    public String getBalanceString() {
        double balance = Reader.getManagerBalance();
        DecimalFormat df = new DecimalFormat("#,###");
        df.setMaximumFractionDigits(3);
        return df.format(balance);
    }

    // set manager's balance
    public void setBalance(double balance) {
        try {
            Writer.updateManagerBalance(balance);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // update manager's balance
    public void updateBalance(double offset) {
        try {
            Writer.updateManagerBalance(getBalanceDouble() + offset);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // add a new stock (name, price, company name) to the stock.txt
    public void addNewStock(String name, double Price, String companyName) {
        String newline = name+" "+Price+" "+companyName;
        try {
            Writer.addLine(newline, "src/data/stock.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // update price for a stock in stock.txt
    public void updateStockPrice(String name, double newPrice) {
        try {
            Writer.updateStockPrice(name, newPrice, "src/data/stock.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



// public static void main(String[] args) throws IOException {
//  addNewStock("ADBE", 344.11, "Adobe");
//  updateStockPrice("PYPL", 73.44);
//  System.out.println(getBalanceString());
//  setBalance(10000000);
//  System.out.println(getBalanceString());
//  updateBalance(200);
//  System.out.println(getBalanceString());
// }
}
