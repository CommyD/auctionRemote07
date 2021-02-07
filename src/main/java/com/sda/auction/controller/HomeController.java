package com.sda.auction.controller;

import com.sda.auction.dto.UserDto;
import com.sda.auction.service.UserService;
import com.sda.auction.validator.UserDtoValidator;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private UserService userService;
    private UserDtoValidator userDtoValidator;

    @Autowired
    public HomeController(UserService userService, UserDtoValidator userDtoValidator) {
        this.userService = userService;
        this.userDtoValidator = userDtoValidator;
    }

    @GetMapping("/home")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("userDto", new UserDto());

        return "register";
    }

    @PostMapping("/register")
    public String postRegisterPage(Model model, UserDto userDto, BindingResult bindingResult){
        userDtoValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "register";
        }
        userService.register(userDto);
        return "redirect:/home";
    }


}
