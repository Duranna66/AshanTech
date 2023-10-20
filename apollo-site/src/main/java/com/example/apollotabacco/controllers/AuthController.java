package com.example.apollotabacco.controllers;


import com.example.apollotabacco.dto.JwtRequest;
import com.example.apollotabacco.dto.JwtResponse;
import com.example.apollotabacco.dto.RegistrationUser;
import com.example.apollotabacco.dto.UserDTO;
import com.example.apollotabacco.entities.Bucket;
import com.example.apollotabacco.entities.User;
import com.example.apollotabacco.exceptions.AppError;
import com.example.apollotabacco.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getLogin(),
                    jwtRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),
                    "что-то пошло не так"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getLogin());

        return ResponseEntity.ok(new JwtResponse(jwtTokenUtils.generateToken(userDetails)));
    }
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUser user) {
        if(!user.getPassword().equals(user.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "not correct confirm password"), HttpStatus.BAD_REQUEST);
        }
        if(userService.findByName(user.getLogin()) != null) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User already exist"), HttpStatus.BAD_REQUEST);
        }
        User newUser = userService.createNewUser(user);
        return ResponseEntity.ok(new UserDTO(newUser.getId(),
                newUser.getName(),
                newUser.getRoles(),
                newUser.getEmail(),
                newUser.getPhoneNumber(),
                newUser.getPassword(),
                newUser.getBucket()));
    }
}
