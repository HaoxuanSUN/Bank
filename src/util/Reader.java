package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Reader {
    private static BufferedReader reader;
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();
    public static List<String> L1 = getLine("src/data/user.txt");
    public static List<String> L2 = getLine("src/data/Stock.txt");
    public static List<String> L3 = getLine("src/data/SecuritiesAccount.txt");
    public static List<String> L4 = getLine("src/data/CheckingAccount.txt");
    public static List<String> L5 = getLine("src/data/SavingAccount.txt");
    public static List<String> L6 = getLine("src/data/LoanAccount.txt");
    public static List<String> L7 = getLine("src/data/log.txt");
    public static List<String> L8 = getLine("src/data/Transaction.txt");

    private static String[] hasTarget(List<String> list, String t) {
        for (String s : list) {
            if (s.split("\\s+", 0)[0].equals(t)) {
                return s.split("\\s+", 0);
            }
        }
        return null;
    }
    public static String[] getStockName(String name) {
        //read from stock by name
        String[] ss = new String[2];
        for (String s : L2) {
            if (s.split(" ", 0)[0].equals(name)) {
                ss[0] = name;
                ss[1] = s.split(" ", 0)[1];
                return ss;
            }
        }
        return null;
    }
    // get manager's balance
    public static double getManagerBalance() {
        double balance = 0.0;
        try {
            reader = new BufferedReader(new java.io.FileReader("src/data/ManagerBalance.txt"));
            String line = reader.readLine();
            balance = Double.parseDouble(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return balance;
    }
    public static List<String> getLine(String path) {
        List<String> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new java.io.FileReader(path));
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null && line.length() != 0) {
                list.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void printLine(List<String> line) {
        for (String l : line) {
            System.out.println(l);
        }
    }
    public static void update(){
        L1 = getLine("src/data/user.txt");
        L2 = getLine("src/data/Stock.txt");
        L3 = getLine("src/data/SecuritiesAccount.txt");
        L4 = getLine("src/data/CheckingAccount.txt");
        L5 = getLine("src/data/SavingAccount.txt");
        L6 = getLine("src/data/LoanAccount.txt");
    }
    public static void printLineFormat5(List<String> line) {
        for (String l : line) {
            String[] s = l.split("\\s+", 0);
            System.out.format(Constants.format5, s);
        }
    }
    public static void printLineFormat4(List<String> line) {
        for (String l : line) {
            String[] s = l.split("\\s+", 0);
            System.out.format(Constants.format4, s);
        }
    }
    public static void printLineFormat6(List<String> line) {
        for (String l : line) {
            String[] s = l.split("\\s+", 0);
            System.out.format(Constants.format6, s);
        }
    }
}

