package chapter3.executeAroundPattern;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by jyami on 2020/07/29
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
