package com.jyami.springbootgettingstartedmaven.webMVC.user;

import org.springframework.web.bind.annotation.*;

/**
 * Created by jyami on 2020/07/13
 */
@RestController()
@RequestMapping("user")
public class UserController {

    @GetMapping()
    public String getUser(){
        return "user";
    }

    @PostMapping("create")
    public @ResponseBody User create(@RequestBody User user){
        return user;
    }

}
