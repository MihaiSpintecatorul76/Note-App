package com.Note_App.Note.App.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class PageController {

    @GetMapping(path="/home")
    public String home() throws IOException {
        return "home";
    }

    @GetMapping(path="/login")
    public String login() throws IOException {
        return "login";
    }

    @GetMapping(path="/register")
    public String register() throws IOException {
        return "register";
    }





}
