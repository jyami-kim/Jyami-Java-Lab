package chatper1;

import chapter1.stream.Currency;
import chapter1.stream.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by jyami on 2020/07/26
 */
@DisplayName("스트림을 활용하여 고가의 트랜잭션만 필터링 한 다음에 통화로 결과를 그룹화 해야하는 경우")
public class SteamTest {

    List<Transaction> transactions = Collections.emptyList();

    @BeforeEach
    void setUp() {
        Currency korea = new Currency("KOR");
        Currency us = new Currency("USA");
        transactions = Collections.unmodifiableList(Arrays.asList(
                new Transaction(900, korea),
                new Transaction(1800, korea),
                new Transaction(2700, korea),
                new Transaction(500, us),
                new Transaction(1000, us),
                new Transaction(1500, us)
        ));
    }

    @Test
    @DisplayName("외부 반복 : 스트림을 활용하지 못하고 컬렉션을 활용해서 그루핑 해야하는 경우")
    void collectionGrouping() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();

        for (Transaction transaction : transactions) {
            if (transaction.getPrice() > 1000) {
                Currency currency = transaction.getCurrency();
                List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
                if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<>();
                    transactionsByCurrencies.put(currency, transactionsForCurrency);
                }
                transactionsForCurrency.add(transaction);
            }
        }

        for (Map.Entry<Currency, List<Transaction>> entry : transactionsByCurrencies.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Test
    @DisplayName("내부 반복 : 스트림을 활용해 그루핑 해야하는 경우")
    void streamGrouping(){
        Map<Currency, List<Transaction>> transactionsByCurrencies =
                transactions.stream()
                .filter(transaction -> transaction.getPrice() > 1000)
                .collect(groupingBy(Transaction::getCurrency));

        for (Map.Entry<Currency, List<Transaction>> entry : transactionsByCurrencies.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

}
