package com.jyami.springbootgettingstartedmaven.webMVC.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jyami on 2020/07/16
 */
@RequestMapping("sample")
@Controller
public class SampleController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("name", "jyami");
        return "hello";
    }

}
