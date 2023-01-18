package data;

import model.accounts.Account;
import util.ID;
import util.Reader;
import util.Writer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class AccountDao {
    private static BufferedReader reader;
    public void save(Account a) throws IOException {
        switch (a.getType()) {
            case SECURITIES:
                Writer.addLine(a.toString(), "src/data/SecuritiesAccount.txt");
                break;
            case CHECKING:
                Writer.addLine(a.toString(), "src/data/CheckingAccount.txt");
                break;
            case SAVINGS:
                Writer.addLine(a.toString(), "src/data/SavingAccount.txt");
                break;
            case LOAN:
                Writer.addLine(a.toString(), "src/data/LoanAccount.txt");
                break;
        }
    }
    public void addStockList(String s, String pathName) throws IOException {
        Writer.addLine(s, "src/data/StockList/" + pathName + ".txt");
    }
    public void buyStock(String path, String newRecord) throws IOException {
        boolean stockFound = false;
        String[] newRecordStringArray = newRecord.split(" ");
        String stockName = newRecordStringArray[2];
        int addStockCount = Integer.parseInt(newRecordStringArray[3]);
        String newStocktxt = "";
        reader = new BufferedReader(new java.io.FileReader("src/data/StockList/" + path + ".txt"));
        String line = reader.readLine();
        newStocktxt += line + "\n";
        line = reader.readLine();
        newStocktxt += line + "\n";
        line = reader.readLine();
        while (line != null && line.length() != 0) {
            String[] temp = line.split(" ");
            // found history activity for the same stock
            if (temp[2].equalsIgnoreCase(stockName)) {
                stockFound = true;
                int newStockCount = Integer.parseInt(temp[3]) + addStockCount;
                String newtext = temp[0] + " " + temp[1] + " " + temp[2] + " " + newStockCount + " " + temp[4] +
                        " " + temp[5] + " " + temp[6] + " " + temp[7] + " " + temp[8] + " " + temp[9];
                line = newtext;
            }
            newStocktxt += line + "\n";
            line = reader.readLine();
        }
        // no activity for the same stock, add string in new line;
        if (!stockFound) {
            newStocktxt += newRecord + "\n";
        }
        PrintWriter writer = new PrintWriter("src/data/StockList/" + path + ".txt");
        writer.print("");
        writer.print(newStocktxt);
        writer.close();
        Reader.update();
    }

    public void createStockListFile(String pathName) {
        File file = new File("src/data/StockList/" + pathName + ".txt");
        boolean result;
        try {
            result = file.createNewFile();
            if(result) {
                System.out.println("file created "+file.getCanonicalPath());
                String s = "id/userID/stockName/number/butDate/sellDate";
                Writer.addLine(s, "src/data/StockList/" + pathName + ".txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
