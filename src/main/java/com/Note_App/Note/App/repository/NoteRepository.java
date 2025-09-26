package com.Note_App.Note.App.repository;

import com.Note_App.Note.App.Model.NoteModel;
import com.Note_App.Note.App.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<NoteModel, Long> {
    List<NoteModel> findAllByUser(UserModel user);

}
