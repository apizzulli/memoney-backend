package com.budgeter.server.Controllers;
import com.budgeter.server.Config.JwtService;
import com.budgeter.server.Config.JwtToken;
import com.budgeter.server.DTO.LoginDTO;
import com.budgeter.server.DTO.UserDTO;
import com.budgeter.server.Entities.Budget;
import com.budgeter.server.Entities.User;
import com.budgeter.server.Repositories.UserRepository;
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

    public UserController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

//    @PostMapping("/generateToken")
//    public String authenticateAndGetToken(@RequestBody User dto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
//        );
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(dto.getUsername());
//        } else {
//            throw new UsernameNotFoundException("Invalid user request!");
//        }
//    }

    public JwtToken generateToken(User user){
        return new JwtToken(jwtService.generateToken(user),jwtService.getExpirationTime());
    }
    //@CrossOrigin(origins="http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDTO login){
        User user = userService.login(login);
        LoginDTO dto = new LoginDTO();
        dto.setBudgets(user.getBudgets());
        dto.setUserId(user.getId());
        dto.setToken(generateToken(user));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/createAccount")
    public ResponseEntity<Object> createAccount(@RequestBody UserDTO info) {
        User newUser = userService.createUser(info);
        userService.login(info);
        return ResponseEntity.ok(new LoginDTO(newUser.getId(), null,generateToken(newUser)));
    }


}
