package com.Note_App.Note.App.Service;

import com.Note_App.Note.App.Dto.UserModelDto;
import com.Note_App.Note.App.Model.UserModel;
import com.Note_App.Note.App.repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Setter @Getter
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserModel register(UserModelDto userModelDto) {
        if (userRepository.findByEmail(userModelDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }
        if (userRepository.findByUsername(userModelDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        if(!userModelDto.getPassword().equals(userModelDto.getRepeatedPassword())) {
            throw new RuntimeException("Passwords do not match!");
        }

        UserModel userModel = new UserModel();
        userModel.setUsername(userModelDto.getUsername());
        userModel.setEmail(userModelDto.getEmail());
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));

        return userRepository.save(userModel);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserModel> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .build();
        }
        else {
            throw new UsernameNotFoundException(email);
        }


    }

}
