package com.jyami;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jyami on 2020/04/24
 */
@Component
public class HolomanRunner implements ApplicationRunner {
    // springboot가 만들어지고 띄었을때 자동으로 실행되는 빈을 만들고 싶다 할 때 사용할 수 있다.

    @Autowired
    Holoman holoman; // 이프로젝트에서 빈으로 넣어주지 않았는데 Holoman이 들어간다!

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(holoman);
    }
}
