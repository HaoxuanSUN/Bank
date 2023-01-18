package util;

import java.io.*;
import java.text.DecimalFormat;

public class Writer {
    private static BufferedReader reader;
    private static BufferedWriter writer;
    public static void addLine(String s, String path) throws IOException {
        writer = new BufferedWriter(new FileWriter(path, true));
        writer.append("\n").append(s);
        writer.close();
        Reader.update();
    }

    public static void updateManagerBalance(double balance) throws IOException {
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(3);
        PrintWriter writer = new PrintWriter("src/data/ManagerBalance.txt");
        writer.print("");
        writer.print(df.format(balance));
        writer.close();
        Reader.update();
    }



    // manager updates stock price
    public static void updateStockPrice(String stockName, double newPrice, String path) throws  IOException{
        String newStocktxt = "";
        reader = new BufferedReader(new java.io.FileReader(path));
        String line = reader.readLine();
        newStocktxt += line + "\n";
        line = reader.readLine();
        while (line != null && line.length() != 0) {
            String[] temp = line.split(" ");
            if (temp[0].equalsIgnoreCase(stockName)) {
                String newtext = temp[0] + " " + newPrice + " " + temp[2];
                line = newtext;
            }
            newStocktxt += line + "\n";
            //System.out.println(newStocktxt);
            line = reader.readLine();
        }
        PrintWriter writer = new PrintWriter(path);
        writer.print("");
        //System.out.println(newStocktxt);
        writer.print(newStocktxt);
        writer.close();
        Reader.update();
    }

//    public static void main(String[] args) throws IOException {
//  //addNewStock("ADBE", 344.11, "Adobe");
//     updateStockPrice("PYPL", 73.444, "src/data/stock.txt");
// }
}
