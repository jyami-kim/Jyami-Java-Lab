package chapter5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by jyami on 2020/08/01
 */
@AllArgsConstructor
@Getter
@ToString
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
}
