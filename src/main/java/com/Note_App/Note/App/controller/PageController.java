package com.Note_App.Note.App.controller;

import com.Note_App.Note.App.Dto.UserModelDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class PageController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }




    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserModelDto());
        return "register";
    }



}
