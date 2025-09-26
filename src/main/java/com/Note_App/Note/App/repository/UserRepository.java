package com.Note_App.Note.App.repository;

import com.Note_App.Note.App.Model.NoteModel;
import com.Note_App.Note.App.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
   Optional<UserModel> findByUsername(String username);
   Optional<UserModel> findByEmail(String email);

}
