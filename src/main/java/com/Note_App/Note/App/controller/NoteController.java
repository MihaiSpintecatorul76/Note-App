package com.Note_App.Note.App.controller;

import com.Note_App.Note.App.Model.NoteModel;
import com.Note_App.Note.App.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {

    public final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
            public String getNotes(Model model) {
       List<NoteModel> notes = noteService.getAllNotes();
        System.out.println("Lista de note: " + notes);
        model.addAttribute("notes", notes);
        return "notes";
    }

    @PostMapping
    public String addNote(@ModelAttribute NoteModel noteModel) {
        noteService.addNote(noteModel);
        return "redirect:/notes";
    }
}


