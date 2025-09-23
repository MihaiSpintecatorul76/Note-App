package com.Note_App.Note.App.controller;

import com.Note_App.Note.App.Dto.UserModelDto;
import com.Note_App.Note.App.Service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserModelDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Validated UserModelDto userModelDto, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            StringBuilder errorsString = new StringBuilder();
            for (var error : result.getFieldErrors()) {
                errorsString.append(String.format("%s %s\n", error.getField(), error.getDefaultMessage()));
            }
            model.addAttribute("regErrorBackend", errorsString.toString());
            return "register";
        }
        try
        {
            userService.register(userModelDto);
            return "redirect:/login";
        }
        catch(IllegalArgumentException error)
        {
            model.addAttribute("regError", error.getMessage());
            return "register";
        }
    }
}
