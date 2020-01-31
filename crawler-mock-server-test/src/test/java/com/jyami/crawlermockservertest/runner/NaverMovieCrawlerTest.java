package com.jyami.crawlermockservertest.runner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/01/31
 */
public class NaverMovieCrawlerTest {

    // 실제 Jsoup 커넥션 대신 html 파일 사용
    public Document getCrawlingResultWithFile(String file){
        File htmlFile = new File(getClass().getClassLoader()
                .getResource(file)
                .getFile());
        try {
            return Jsoup.parse(htmlFile, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("crawling 실패");
        }
    }

}