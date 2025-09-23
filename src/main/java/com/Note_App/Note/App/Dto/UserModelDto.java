package com.Note_App.Note.App.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@NoArgsConstructor
public class UserModelDto {
    private Long id;
    @NotBlank(message = "Please enter a username")
    @Size(min = 4, max = 25)
    private String username;
    private String password;
    private String repeatedPassword;
    @NotBlank(message = "Please enter a valid email")
    @Email(message = "Please enter a valid email")
    private String email;
}
