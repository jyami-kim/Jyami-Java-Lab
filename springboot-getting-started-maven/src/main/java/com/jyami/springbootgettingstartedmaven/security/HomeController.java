package com.jyami.springbootgettingstartedmaven.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jyami on 2020/07/21
 */
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/my")
    public String my(){
        return "my";
    }
}
