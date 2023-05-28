package sn.niit.restauranManagementApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.niit.restauranManagementApplication.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/list")
    public String showUserList(Model model) {
        model.addAttribute("userService", userService);
        return "admin/user-list";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("userService", userService);

        return "admin/user-new";
    }

}
