package com.budgeter.server.Controllers;
import com.budgeter.server.Config.JwtService;
import com.budgeter.server.Config.JwtToken;
import com.budgeter.server.DTO.LoginRequest;
import com.budgeter.server.DTO.LoginResponse;
import com.budgeter.server.DTO.LoginResponse;
import com.budgeter.server.Entities.Budget;
import com.budgeter.server.Entities.User;
import com.budgeter.server.Repositories.UserRepository;
import com.budgeter.server.Services.EmailService;
import com.budgeter.server.Services.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/user")
@RestController
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;
    private final EmailService emailService;
    public UserController(JwtService jwtService, UserService userService, EmailService emailService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.emailService = emailService;
    }

    public JwtToken generateToken(User user){
        return new JwtToken(jwtService.generateToken(user),jwtService.getExpirationTime());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request){
        User user = userService.login(request);
        if(user == null){
            return new ResponseEntity<>("No such user", HttpStatus.NOT_FOUND);
        }
//        LoginResponse dto = new LoginResponse();
//        dto.setToken(generateToken(user));
        return new ResponseEntity<>(new LoginResponse(user, generateToken(user)), HttpStatus.OK);
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Object> createAccount(@RequestBody LoginRequest info) {
        User newUser = userService.createUser(info);
        JwtToken token = generateToken(newUser);
        this.emailService.sendAccountCreated(info.getUsername());
        return new ResponseEntity<>(new LoginResponse(newUser, token), HttpStatus.CREATED);
    }

    @GetMapping("/testEmail")
    public String testEmail(){ 
        this.emailService.sendSimpleMessage();
        return "Hello from /budgets/test";
    }

}
