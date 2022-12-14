package com.example.teste.exception.exceptionHandler;

import com.example.teste.exception.negocioException.ExceptionBadRequest;
import com.example.teste.exception.negocioException.NegocioException;
import com.example.teste.util.DateUtil;
import com.example.teste.util.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    private DateUtil dateUtil;

    private StringUtil stringUtil;

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
        responseError.setDataHora(dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        responseError.setTitulo("Campo Obrigat??rio");
        responseError.setError(status.getReasonPhrase());
        responseError.setCampos(campos);
        responseError.setDetalhes(stringUtil.formatString(45,ex.getClass().getName()));

        return handleExceptionInternal(ex,responseError,headers, status, request);
    }

    @ExceptionHandler(ExceptionBadRequest.class)
    public ResponseEntity<Object> badRequest(NegocioException ex, WebRequest webRequest){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ResponseError responseError = new ResponseError();
        responseError.setStatus(status.value());
        responseError.setDataHora(dateUtil.formatLocalDateTimeToDatabesseStyle(LocalDateTime.now()));
        responseError.setTitulo(ex.getMessage());
        responseError.setError(status.getReasonPhrase());
        responseError.setDetalhes(stringUtil.formatString(45,ex.getClass().getName()));

        return handleExceptionInternal(ex,responseError,headers,status,webRequest);

    }


}
