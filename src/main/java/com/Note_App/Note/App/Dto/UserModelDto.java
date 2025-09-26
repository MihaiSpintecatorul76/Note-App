package com.Note_App.Note.App.Dto;


import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@NoArgsConstructor
public class UserModelDto {
    private Long id;
    @NotBlank(message = "Please enter a username")
    private String username;
    @NotBlank(message = "Please enter a password")
    private String password;

    private String repeatedPassword;
    @NotBlank(message = "Please enter an email")
    @Email(message = "Please enter a valid email")
    private String email;
}
