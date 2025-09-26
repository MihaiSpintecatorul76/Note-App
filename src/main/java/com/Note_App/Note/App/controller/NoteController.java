package com.Note_App.Note.App.controller;

import com.Note_App.Note.App.Model.NoteModel;
import com.Note_App.Note.App.Model.UserModel;
import com.Note_App.Note.App.Service.NoteService;
import com.Note_App.Note.App.repository.NoteRepository;
import com.Note_App.Note.App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notes")
public class NoteController {

    public final NoteService noteService;
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteService noteService, UserRepository userRepository, NoteRepository noteRepository) {
        this.noteService = noteService;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;

    }

    @GetMapping
            public String getNotes(Model model) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserModel user = userRepository.findByUsername(authentication.getName()).get();
        List<NoteModel> notes = noteService.getAllNotesForCurrentUser(user);
        model.addAttribute("notes", notes);
        model.addAttribute("user",noteService.getAllNotesForCurrentUser(user));
        model.addAttribute("username", authentication.getName());
        model.addAttribute("hasNotes", !notes.isEmpty());

        return "notes";
    }

    @GetMapping("/create")
    public String createNote(Model model) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();


        model.addAttribute("noteObject", new NoteModel());
        model.addAttribute("username", authentication.getName());
        return "create";
    }

    @PostMapping("/create/add")
    public String addNote(@ModelAttribute NoteModel noteModel) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserModel user=userRepository.findByUsername(username).get();
        noteModel.setUser(user);
        noteService.addNote(noteModel);
        return "redirect:/notes";
    }
}


