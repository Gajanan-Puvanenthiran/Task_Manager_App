package com.gajanan.Task.Manager.App.service.impl;

import com.gajanan.Task.Manager.App.model.dto.UserDTO;
import com.gajanan.Task.Manager.App.model.entity.User;
import com.gajanan.Task.Manager.App.repository.UserRepository;
import com.gajanan.Task.Manager.App.service.AuthService;
import com.gajanan.Task.Manager.App.util.AuthenticationRequest;
import com.gajanan.Task.Manager.App.util.AuthenticationResponse;
import com.gajanan.Task.Manager.App.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean hasUserWithUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public String register(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return user.getUsername();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
        var user=userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        String jwtToken=jwtUtil.generateToken(user);

        return AuthenticationResponse.builder().jwt(jwtToken).build();
    }
}
