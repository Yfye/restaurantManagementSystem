package sn.niit.restauranManagementApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.dto.UserDto;
import sn.niit.restauranManagementApplication.service.UserService;
import sn.niit.restauranManagementApplication.serviceImpl.CartServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class AuthController {
    private UserService userService;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user-login")
    public String user_login() {
        return "site/login";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/user-register")
    public String employeeRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "site/user-register";
    }

    // handler method to handle user registration form submit request
    @PostMapping(value = "/register/save-emp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto, "employee");
        return "redirect:/register?success";
    }

    @PostMapping(value = "/register/save-user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String employeeRegistration(@Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/user-register";
        }

        System.out.println(userDto.getPrenom() + userDto.getNom() + userDto.getEmail() + userDto.getPassword());
        userService.saveUser(userDto, "user");
        if (cartServiceImpl.doesSessionCartExist()) {
            cartServiceImpl.assignSessionCartToUser(userDto.getEmail());
        }

        return "redirect:/user-register?success";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }
}
