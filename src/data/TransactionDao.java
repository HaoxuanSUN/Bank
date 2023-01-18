package data;

import model.transactions.Transaction;
import util.Writer;

import java.io.IOException;

public class TransactionDao {
    public void save(Transaction t) throws IOException {
        Writer.addLine(t.toString(), "src/data/Transaction.txt");
    }
}
