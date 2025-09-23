package com.Note_App.Note.App.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String username;
   private String password;
   private String repeatedPassword;
   private String email;
}
