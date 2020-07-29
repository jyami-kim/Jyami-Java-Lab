package chapter3;

import chapter3.executeAroundPattern.FileProcessor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jyami on 2020/07/28
 */
public class ExecuteAroundPatternTest {
    @Test
    @DisplayName("실행 어라운드 패턴 1번째")
    void name() throws IOException {
        String s = FileProcessor.processFile1();
        assertThat(s).isEqualTo("line1");
    }

    @Test
    @DisplayName("실행 어라운드 패턴 개선1")
    void name2() throws IOException {
        String s = FileProcessor.processFile2(br -> br.readLine());
        assertThat(s).isEqualTo("line1");
    }

    @Test
    @DisplayName("실행 어라운드 패턴 개선2")
    void name3() throws IOException {
        String s = FileProcessor.processFile2(br -> br.readLine() + br.readLine());
        assertThat(s).isEqualTo("line1line2");
    }
}
