package xyz.teamgravity.people.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xyz.teamgravity.people.model.ExceptionModel;

import java.time.ZonedDateTime;

@ControllerAdvice
public class PersonExceptionHandler {

    @ExceptionHandler(value = {PersonNotExistException.class})
    public ResponseEntity<Object> handlePersonNotExistException(PersonNotExistException e) {
        ExceptionModel model = new ExceptionModel(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
    }
}
