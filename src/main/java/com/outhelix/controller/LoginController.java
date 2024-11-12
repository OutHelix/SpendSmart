package com.outhelix.controller;

import com.outhelix.model.User;
import com.outhelix.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping
    public String loginUser(@ModelAttribute User user, Model model, HttpSession session) {
        User loginningUser = userService.findByEmailOrUsername(user.getUsername());

        if (loginningUser != null && loginningUser.getPassword().equals(user.getPassword())) {
            session.setAttribute("loggedInUser", loginningUser);
            return "redirect:/homepage";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }
}
