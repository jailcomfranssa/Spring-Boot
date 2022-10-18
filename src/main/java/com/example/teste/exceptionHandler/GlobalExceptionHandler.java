package com.example.teste.exceptionHandler;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<CampoObrigatorioException> campos = new ArrayList<>();

        for (ObjectError error: ex.getBindingResult().getAllErrors()){

            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new CampoObrigatorioException(nome,mensagem));
        }

        ResponseError responseError = new ResponseError();
        responseError.setStatus(status.value());
        responseError.setDataHora(LocalDateTime.now());
        responseError.setTitulo("Campo Obrigatório");
        responseError.getError();
        responseError.setCampos(campos);

        return handleExceptionInternal(ex,responseError,headers, status, request);
    }
}
