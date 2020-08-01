package chapter5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/08/01
 */
public class PracticeTest {

    @Test
    @DisplayName("1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오")
    void name1() {

        List<Transaction> collect = TransactionFactory.getInstance().getTransactions().stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    @DisplayName("2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시")
    void name2() {

        List<String> collect = TransactionFactory.getInstance().getTransactions().stream()
                .map(x -> x.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(collect);

    }

    @Test
    @DisplayName("3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오")
    void name3() {
        List<Trader> collect = TransactionFactory.getInstance().getTransactions().stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(x -> "Cambridge".equals(x.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    @DisplayName("4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오")
    void name4() {
        String collect = TransactionFactory.getInstance().getTransactions().stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(collect);
    }

    @Test
    @DisplayName("5. 밀라노에 거래자가 있는가?")
    void name5() {
        boolean b = TransactionFactory.getInstance().getTransactions().stream()
                .map(Transaction::getTrader)
                .anyMatch(x -> "Milan".equals(x.getCity()));
        System.out.println(b);
    }

    @Test
    @DisplayName("6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오")
    void name6() {
        List<Integer> collect = TransactionFactory.getInstance().getTransactions().stream()
                .filter(x -> "Cambridge".equals(x.getTrader().getCity()))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    @DisplayName("7. 전체 트랜잭션 중 최대값은 얼마인가")
    void name7() {
        Optional<Integer> max = TransactionFactory.getInstance().getTransactions().stream()
                .map(Transaction::getValue)
                .max(Integer::compare);

        Optional<Transaction> max1 = TransactionFactory.getInstance().getTransactions().stream()
                .max(Comparator.comparingInt(Transaction::getValue));

        Optional<Integer> reduce = TransactionFactory.getInstance().getTransactions().stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        System.out.println(max);
    }

    @Test
    @DisplayName("8. 전체 트랜잭션 중 최솟값은 얼마인가")
    void name8() {
        Optional<Integer> min = TransactionFactory.getInstance().getTransactions().stream()
                .map(Transaction::getValue)
                .min(Integer::compare);

        Optional<Transaction> min1 = TransactionFactory.getInstance().getTransactions().stream()
                .min(Comparator.comparingInt(Transaction::getValue));

        Optional<Integer> reduce = TransactionFactory.getInstance().getTransactions().stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);

        System.out.println(min);
    }


}
