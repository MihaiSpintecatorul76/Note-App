package com.Note_App.Note.App.Service;

import com.Note_App.Note.App.Model.NoteModel;
import com.Note_App.Note.App.Model.UserModel;
import com.Note_App.Note.App.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteModel> getAllNotesForCurrentUser(UserModel user) {
        return noteRepository.findAllByUser(user);
    }

    public void addNote(NoteModel noteModel) {
        noteRepository.save(noteModel);
    }
}
