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
public class Trader {
    private final String name;
    private final String city;
}
