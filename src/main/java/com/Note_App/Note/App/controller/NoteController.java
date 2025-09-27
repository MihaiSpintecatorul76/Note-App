package com.Note_App.Note.App.controller;

import com.Note_App.Note.App.Model.NoteModel;
import com.Note_App.Note.App.Model.UserModel;
import com.Note_App.Note.App.Service.NoteService;
import com.Note_App.Note.App.Service.UserService;
import com.Note_App.Note.App.repository.NoteRepository;
import com.Note_App.Note.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NoteController {

    public final NoteService noteService;
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private final UserService userService;

    @Autowired
    public NoteController(NoteService noteService, UserRepository userRepository, NoteRepository noteRepository, UserService userService) {
        this.noteService = noteService;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    @GetMapping
    public String getNotes(Model model) {

        UserModel user = userService.getUser();
        List<NoteModel> notes = noteService.getAllNotesForCurrentUser(user);
        model.addAttribute("notes", notes);
        model.addAttribute("user", user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("hasNotes", !notes.isEmpty());

        return "notes";
    }

    @GetMapping("/create")
    public String createNote(Model model) {
        UserModel user = userService.getUser();
        model.addAttribute("noteObject", new NoteModel());
        model.addAttribute("username", user.getUsername());
        return "create";
    }

    @PostMapping("/create/add")
    public String addNote(@ModelAttribute NoteModel noteModel) {

        UserModel user = userService.getUser();
        noteModel.setUser(user);
        noteService.addNote(noteModel);
        return "redirect:/notes";
    }

    @GetMapping("/{id}")
    public String editNote(Model model, @PathVariable Long id) {
        UserModel user = userService.getUser();
        NoteModel noteModel = noteRepository.findByUserAndId(user,id);
        model.addAttribute("note", noteModel);
        return "view";
    }

    @PostMapping("/{id}/edit")
    public String editNote(@ModelAttribute NoteModel noteModel, @PathVariable Long id) {
        UserModel user = userService.getUser();
        noteModel.setUser(user);
        noteModel.setId(id);
        noteService.addNote(noteModel);
        return "redirect:/notes";
    }

//    @PostMapping("/{id}/delete")
//    public String eraseNote(@ModelAttribute NoteModel noteModel, @PathVariable Long id) {
//        UserModel user = userService.getUser();
//        noteModel.setUser(user);
//        noteModel.setId(id);
//        noteService.deleteNote(id);
//        return "redirect:/notes";
//    }

    @GetMapping("{id}/delete")
    public String eraseNote(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
        return "redirect:/notes";
    }
}


