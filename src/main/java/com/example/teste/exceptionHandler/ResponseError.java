package com.example.teste.exceptionHandler;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class ResponseError {

    private LocalDateTime dataHora;
    private Integer status;
    private final String error = "error";
    private String titulo;
    private List <CampoObrigatorioException> campos;
}
