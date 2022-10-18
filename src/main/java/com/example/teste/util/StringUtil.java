package com.example.teste.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

    public String formatString(int tamanho, String palavra){
        return palavra.substring(tamanho, palavra.length());

    }
}
