package com.Note_App.Note.App.controller;

import com.Note_App.Note.App.Dto.UserModelDto;
import com.Note_App.Note.App.Service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {

    private final UserService userService;

UserController(UserService userService) {
    this.userService = userService;
}

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid UserModelDto userModelDto, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (var error : result.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            model.addAttribute("regErrorBackend", errors); 
            return "register";
        }
        try
        {
            userService.register(userModelDto);
            return "redirect:/login";
        }
        catch(RuntimeException error)
        {
            model.addAttribute("regErrorBackend", error.getMessage());
            return "register";
        }
    }
}
