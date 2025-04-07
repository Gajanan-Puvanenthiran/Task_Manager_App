package com.gajanan.Task.Manager.App.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    @NotNull(message = "Title is Mandatory")
    @NotBlank(message = "Title is Mandatory")
    private String title;

    @NotNull(message = "Description is Mandatory")
    @NotBlank(message = "Description is Mandatory")
    private String description;

    @NotNull(message = "Status is Mandatory")
    @NotBlank(message = "Status is Mandatory")
    private String status;

}
