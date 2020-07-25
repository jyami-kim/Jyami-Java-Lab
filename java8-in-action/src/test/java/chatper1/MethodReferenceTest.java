package chatper1;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2020/07/26
 */
@DisplayName("디렉터리에서 모든 숨겨진 파일을 필터링.")
class MethodReferenceTest {

    @Test
    @DisplayName("메서드 레퍼런스를 사용하기 전, 익명 클래스를 사용하는 방법")
    void test1() {
        File[] hiddenFile = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        System.out.println(Arrays.toString(hiddenFile));
    }

    @Test
    @DisplayName("메서드 레퍼런스를 사용하는 방법")
    void test2() {
        File[] hiddenFile = new File(".").listFiles(File::isHidden);
        System.out.println(Arrays.toString(hiddenFile));
    }
}