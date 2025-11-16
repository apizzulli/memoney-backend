package com.memoney.server.Services;

import com.memoney.server.DTO.LoginRequest;
import com.memoney.server.Entities.User;
import com.memoney.server.Repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User login(LoginRequest dto) {
        if(userRepository.findByUsername(dto.getUsername())==null){
            return null;
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );
        return userRepository.findByUsername(dto.getUsername());
    }

    public User createUser(LoginRequest input) {
        User found = userRepository.findByUsername(input.getUsername());
        if(userRepository.findByUsername(input.getUsername()) != null){
            return null;
        }
        User user = new User();
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        userRepository.save(user);
        return user;
        //return new LoginResponse(user.getId(), null, )
    }

}
