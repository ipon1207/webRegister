package com.example.webregister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // ログインページを表示
    @GetMapping("/toLogin")
    public String showLoginPage() {
        return "toLogin";
    }

    @GetMapping("/main")
    public String showMainPage() {
        return "main";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

}