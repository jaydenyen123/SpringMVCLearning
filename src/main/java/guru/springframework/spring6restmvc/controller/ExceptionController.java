package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.exception.PresentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(PresentException.class)
    public ResponseEntity handleNotFoundException() {
        return ResponseEntity.badRequest().build();
    }
}
