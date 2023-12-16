package dev.ayanfe.taskmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TaskAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;
//    public TaskAPIException(String message) {super(message);}
}
