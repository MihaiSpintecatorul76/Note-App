package com.Note_App.Note.App.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class NoteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title;
    String content;
}
