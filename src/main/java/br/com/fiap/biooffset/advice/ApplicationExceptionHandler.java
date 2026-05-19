package br.com.fiap.biooffset.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    // Classe para tratar exceptions
    // Pode ser usada para tratar qualquer exception, basta criar o método

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException error) {

        Map<String, String> errorMap = new HashMap<>();

        List<FieldError> camposErro = error.getBindingResult().getFieldErrors();

        for (FieldError campo : camposErro) {
            errorMap.put(campo.getField(), campo.getDefaultMessage());
        }

        return errorMap;

    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleIntegrityViolation() {

        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("erro", "Algum dado inserido está inválido e/ou já foi cadastrado.");

        return errorMap;
    }
}
