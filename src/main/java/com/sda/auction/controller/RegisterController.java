package com.sda.auction.controller;

import com.sda.auction.dto.UserDto;
import com.sda.auction.service.UserService;
import com.sda.auction.validator.UserDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class RegisterController {

    private final UserService userService;
    private final UserDtoValidator userDtoValidator;

    @Autowired
    public RegisterController(UserService userService, UserDtoValidator userDtoValidator) {
        this.userService = userService;
        this.userDtoValidator = userDtoValidator;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        log.info("getRegisterPage called");
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String postRegisterPage(Model model, UserDto userDto, BindingResult bindingResult){
        log.info("postRegisterPage called");
        userDtoValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "register";
        }
        log.info("User registered successfully");
        userService.register(userDto);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, UserDto userDto) {
        log.info("getLoginPage called");
        model.addAttribute("userDto", userDto);
        return "login";
    }
}
