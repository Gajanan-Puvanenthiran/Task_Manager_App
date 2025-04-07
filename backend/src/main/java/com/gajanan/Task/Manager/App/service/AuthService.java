package com.gajanan.Task.Manager.App.service;

import com.gajanan.Task.Manager.App.model.dto.UserDTO;
import com.gajanan.Task.Manager.App.util.AuthenticationRequest;
import com.gajanan.Task.Manager.App.util.AuthenticationResponse;

public interface AuthService {

    boolean hasUserWithUsername(String username);

    String register(UserDTO userDTO);

    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
