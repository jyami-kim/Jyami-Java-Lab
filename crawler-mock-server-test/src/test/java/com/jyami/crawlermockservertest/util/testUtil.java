package com.jyami.crawlermockservertest.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * Created by jyami on 2020/01/31
 */
public class testUtil {
    // 실제 Jsoup 커넥션 대신 html 파일 사용
    public static Document getCrawlingResultWithFile(String file){
        File htmlFile = new File(testUtil.class.getClassLoader()
                .getResource(file)
                .getFile());
        try {
            return Jsoup.parse(htmlFile, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("crawling 실패");
        }
    }
}
