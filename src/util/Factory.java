package util;
import model.stock.Stock;

public class Factory {

    public static Stock produceStock(String name) {
        String[] info = Reader.getStockName(name);
        return info == null ? null : new Stock(info[0], Double.parseDouble(info[1]));
    }
}