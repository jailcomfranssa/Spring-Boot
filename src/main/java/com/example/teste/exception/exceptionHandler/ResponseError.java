package com.example.teste.exception.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError {

    private String dataHora;
    private Integer status;
    private String error = "error";
    private String titulo;
    private String detalhes;
    private String developerMessage;
    private List <CampoObrigatorioException> campos;
}
