package com.example.teste.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    private Long id;

    private String nome;
    private String email;
    private String login;
}
