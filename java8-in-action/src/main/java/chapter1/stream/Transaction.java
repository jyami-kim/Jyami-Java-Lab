package chapter1.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by jyami on 2020/07/26
 */
@Getter
@AllArgsConstructor
@ToString
public class Transaction {
    private int price;
    private Currency currency;
}
