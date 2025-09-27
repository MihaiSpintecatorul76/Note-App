package com.Note_App.Note.App.Service;

import com.Note_App.Note.App.Dto.UserModelDto;
import com.Note_App.Note.App.Model.UserModel;
import com.Note_App.Note.App.repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Setter
@Getter
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel getUser()
    {
        Authentication  authentication= SecurityContextHolder.getContext().getAuthentication();
        return  userRepository.findByUsername(authentication.getName()).orElseThrow();
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())

                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }


    }

    public UserModel register(UserModelDto userModelDto) {
        if (userRepository.findByUsername(userModelDto.getUsername()).isPresent() && userRepository.findByEmail(userModelDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email and username already exist!");
        }
        else if (userRepository.findByEmail(userModelDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        else if (userRepository.findByUsername(userModelDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        if (userModelDto.getPassword() == null || userModelDto.getRepeatedPassword() == null
                || !userModelDto.getPassword().equals(userModelDto.getRepeatedPassword())) {
            throw new IllegalArgumentException("Passwords do not match!");
        }


        UserModel userModel = new UserModel();
        userModel.setUsername(userModelDto.getUsername());
        userModel.setEmail(userModelDto.getEmail());
        userModel.setPassword(passwordEncoder.encode(userModelDto.getPassword()));

        return userRepository.save(userModel);
    }



}
