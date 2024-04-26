package com.example.Traini8.advice;
import com.example.Traini8.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation. ResponseStatus;
import org.springframework.web.bind.annotation. RestController;
import org.springframework.web.bind.annotation. RestControllerAdvice;
import java.util.*;

@RestControllerAdvice
public class AppExceptionHandler {
    @ResponseStatus(HttpStatus. BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument (MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> handleException(UserNotFoundException e){
        Map<String,String> errorMap= new HashMap<>();
        errorMap.put("error message", e.getMessage());
        return errorMap;
    }
}