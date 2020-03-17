package ua.nure.hrynko.walletservice.exceptions.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;

/**
 * Advice for returning exception message and correct httpStatus in case of error
 */
@ControllerAdvice
public class TransactionExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(TransactionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String transactionHandler(TransactionException ex) {
        return ex.getMessage();
    }
}
