package com.gajanan.Task.Manager.App.controller;

import com.gajanan.Task.Manager.App.model.dto.UserDTO;
import com.gajanan.Task.Manager.App.service.AuthService;
import com.gajanan.Task.Manager.App.util.AuthenticationRequest;
import com.gajanan.Task.Manager.App.util.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String>register(@RequestBody UserDTO userDTO) {
        if(authService.hasUserWithUsername(userDTO.getUsername())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Username already taken");
        }
        String username=authService.register(userDTO);
        if(username==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
        }
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse>login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authResponse=authService.login(authenticationRequest);
        return ResponseEntity.ok(authResponse);
    }
}
