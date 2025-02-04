package com.example.webregister.controller.admin;

import com.example.webregister.model.User;
import com.example.webregister.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping
    public String showAdminPage() {
        return "admin/admin";
    }

    @GetMapping("/userList")
    public String showUserListPage(Model model) {

        List<User> userList;
        userList = userService.searchAll();
        model.addAttribute("userList", userList);
        return "admin/userList";

    }

    @PostMapping("/userEdit")
    public String showUserEditPage(@RequestParam("id") Long editId, Model model) {

        User editUser;
        editUser = userService.searchById(editId);
        model.addAttribute("editUser", editUser);
        return "admin/userEdit";

    }

    @PostMapping("/userEdit/updateUser")
    public String updateUser(@ModelAttribute User editUser) {

        userService.updateById(editUser);
        return "redirect:/admin/userList";

    }

    @DeleteMapping("/userDelete")
    public String deleteUser(@RequestParam("id") Long deleteId) {

        userService.deleteById(deleteId);
        return "redirect:/admin/userList";

    }

    @GetMapping("/userRegister")
    public String showRegisterPage(Model model) {

        model.addAttribute("saveUser", new User());
        return "admin/userRegister";

    }

    @PostMapping("/userRegister/save")
    public String userSave(@ModelAttribute User saveUser, Model model) {

        userService.insertUser(saveUser);
        return "admin/admin";

    }

}