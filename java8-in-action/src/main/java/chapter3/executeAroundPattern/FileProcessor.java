package chapter3.executeAroundPattern;

import java.io.*;

/**
 * Created by jyami on 2020/07/28
 */
public class FileProcessor {

    public static String processFile1() throws IOException {
        InputStream fileResourceAsStream = FileProcessor.class.getClassLoader().getResourceAsStream("data.txt");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(fileResourceAsStream))){
            return br.readLine();
        }
    }

    public static String processFile2(BufferedReaderProcessor p) throws IOException {
        InputStream fileResourceAsStream = FileProcessor.class.getClassLoader().getResourceAsStream("data.txt");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(fileResourceAsStream))){
            return p.process(br);
        }
    }
}
