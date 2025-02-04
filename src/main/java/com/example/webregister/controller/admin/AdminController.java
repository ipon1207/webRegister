package com.example.webregister.controller.admin;

import com.example.webregister.model.User;
import com.example.webregister.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin/admin";
    }

    @GetMapping("/admin/userList")
    public String showUserListPage(Model model) {

        List<User> userList;
        userList = userService.searchAll();
        model.addAttribute("userList", userList);
        return "admin/userList";

    }

    @PostMapping("/admin/userEdit")
    public String showUserEditPage(@RequestParam("id") Long editId, Model model) {

        User editUser;
        editUser = userService.searchById(editId);
        model.addAttribute("editUser", editUser);
        return "admin/userEdit";

    }

    @PostMapping("/admin/userEdit/updateUser")
    public String updateUser(@ModelAttribute User editUser, Model model) {

        userService.updateById(editUser);
        List<User> userList;
        userList = userService.searchAll();
        model.addAttribute("userList", userList);
        return "admin/userList";

    }

    @DeleteMapping("/admin/userDelete")
    public String deleteUser(@RequestParam("id") Long deleteId, Model model) {

        userService.deleteById(deleteId);
        List<User> userList;
        userList = userService.searchAll();
        model.addAttribute("userList", userList);
        return "admin/userList";

    }

}