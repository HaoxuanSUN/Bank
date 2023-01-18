package data;

import util.Reader;

public class StockDao {
    public double getPriceByName(String name) {
        for (String ss : Reader.L2) {
            String[] sss = ss.split(" ", 0);
            if (sss[0].equals(name)) {
                return Double.parseDouble(sss[1]);
            }
        }
        return 0.0;
    }
}
