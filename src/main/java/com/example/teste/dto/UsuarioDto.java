package com.example.teste.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    private Long id;

    @Size(min = 3, max = 100)
    @NotEmpty
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotEmpty
    private String login;
}
