package com.epam.hotelbookingspring.controller;

import com.epam.hotelbookingspring.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
@RequestMapping("/client")
public class ClientController {

    private UserServiceImpl userService;

    @GetMapping("/index")
    public String wow() {
        return "clientStartPage";
    }

    @Autowired
    public ClientController(UserServiceImpl userService) {
        this.userService = userService;
    }


//    @PostMapping("/login")
//    public String login(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
//        Optional<User> user1 = userService.login(user);
//        if (user1.isPresent()) {
//            redirectAttributes.addFlashAttribute("name", user1.get().getName());
//            return "redirect:/";
//        } else {
//            return "index";
//        }
//    }

//    @GetMapping("/login")
//    public ModelAndView getLoginPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", new User());
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }
}