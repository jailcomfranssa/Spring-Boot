package com.example.teste.exception.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CampoObrigatorioException {

    private String nome;
    private String mensagem;



}
