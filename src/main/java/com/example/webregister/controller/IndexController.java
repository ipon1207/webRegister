package com.example.webregister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    // トップページの表示
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

}
