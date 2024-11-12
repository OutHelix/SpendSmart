package com.outhelix.controller;

import com.outhelix.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage")
public class HomePageController {

    @GetMapping
    public String showHomePage(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
            return "homepage";
        } else {
            return "redirect:/login";
        }
    }
}
