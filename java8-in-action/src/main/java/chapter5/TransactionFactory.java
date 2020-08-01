package chapter5;

import lombok.AccessLevel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jyami on 2020/08/01
 */

public class TransactionFactory {

    private static volatile TransactionFactory transactionFactory = new TransactionFactory();

    private List<Transaction> transactions;

    private TransactionFactory(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    public static TransactionFactory getInstance(){
        return transactionFactory;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
