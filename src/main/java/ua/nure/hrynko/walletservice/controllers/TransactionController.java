package ua.nure.hrynko.walletservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.nure.hrynko.walletservice.dto.TransactionDTO;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;
import ua.nure.hrynko.walletservice.service.PlayerService;

@RestController
public class TransactionController {
    private PlayerService playerService;

    TransactionController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Adds transaction
     *
     * In general post requests are not idempotent, but such behaviour is required here
     * @param transactionDTO - data of request here
     * @return ResponseEntity<?> of transaction added, or error message
     * @throws TransactionException in case if there is not enough balance or transaction ID is mentioned twice
     */
    @PostMapping("/transaction")
    public ResponseEntity<?> addTransaction(@RequestBody @Validated TransactionDTO transactionDTO)
            throws TransactionException {
        return playerService.addTransaction(transactionDTO);
    }
}