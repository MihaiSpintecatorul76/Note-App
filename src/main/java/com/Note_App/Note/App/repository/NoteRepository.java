package com.Note_App.Note.App.repository;

import com.Note_App.Note.App.Model.NoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteModel, Long> {
}
