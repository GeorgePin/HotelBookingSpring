package com.epam.hotelbookingspring.controller;

import com.epam.hotelbookingspring.model.User;
import com.epam.hotelbookingspring.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Controller
@ControllerAdvice
@RequestMapping("/user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
        Optional<User> user1 = userService.login(user);
        if (user1.isPresent()) {
            redirectAttributes.addFlashAttribute("name", user1.get().getName());
            return "redirect:/";
        } else {
            return "index";
        }
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }
}