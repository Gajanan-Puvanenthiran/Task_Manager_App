package com.gajanan.Task.Manager.App.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "username is Mandatory")
    @NotBlank(message = "username is Mandatory")
    private String username;

    @NotNull(message = "password is Mandatory")
    @NotBlank(message = "password is Mandatory")
    private String password;
}
