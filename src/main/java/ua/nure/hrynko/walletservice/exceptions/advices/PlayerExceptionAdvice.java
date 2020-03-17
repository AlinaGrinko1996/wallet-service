package ua.nure.hrynko.walletservice.exceptions.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.nure.hrynko.walletservice.exceptions.PlayerException;

@ControllerAdvice
public class PlayerExceptionAdvice {
        @ResponseBody
        @ExceptionHandler(PlayerException.class)
        @ResponseStatus(HttpStatus.FORBIDDEN)
        String playerHandler(PlayerException ex) {
            return ex.getMessage();
        }
}
