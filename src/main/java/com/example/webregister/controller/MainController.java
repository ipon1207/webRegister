package com.example.webregister.controller;

import com.example.webregister.model.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // ログインページを表示
    @GetMapping("/toLogin")
    public String showLoginPage() {
        return "toLogin";
    }

    @GetMapping("/main")
    public String showMainPage(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {

        String userName;
        userName = userPrincipal.getUsername();
        model.addAttribute("userName", userName);
        return "main";

    }

}