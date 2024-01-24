package com.root.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookException.class)
    public ResponseEntity<String > bookExceptionHandler(BookException be)
    {
        return new ResponseEntity<>(be.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorException.class)
    public ResponseEntity<String > authorExceptionHandler(AuthorException ae)
    {
        return new ResponseEntity<>(ae.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails > validationExceptionHandler(MethodArgumentNotValidException me)
    {
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),"Validation Error",me.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails > noHandlerExceptionHandler(NoHandlerFoundException nfe, WebRequest req)
    {

        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails > exceptionHandler(Exception e,WebRequest req)
    {
        MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),e.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
