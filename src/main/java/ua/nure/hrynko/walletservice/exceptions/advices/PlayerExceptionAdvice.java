package ua.nure.hrynko.walletservice.exceptions.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.nure.hrynko.walletservice.exceptions.PlayerException;

public class PlayerExceptionAdvice {

    @ControllerAdvice
    class EmployeeNotFoundAdvice {

        @ResponseBody
        @ExceptionHandler(PlayerException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String playerHandler(PlayerException ex) {
            return ex.getMessage();
        }
    }
}
