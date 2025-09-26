package com.Note_App.Note.App.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Entity
@Data
@Table(name = "notes")
public class NoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserModel user;


}
