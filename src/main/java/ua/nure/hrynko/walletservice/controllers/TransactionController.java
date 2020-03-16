package ua.nure.hrynko.walletservice.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.hrynko.walletservice.dto.TransactionDTO;
import ua.nure.hrynko.walletservice.entities.Player;
import ua.nure.hrynko.walletservice.entities.Transaction;
import ua.nure.hrynko.walletservice.enums.TransactionType;
import ua.nure.hrynko.walletservice.exceptions.PlayerException;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;
import ua.nure.hrynko.walletservice.repositories.PlayersRepository;
import ua.nure.hrynko.walletservice.repositories.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class TransactionController {

    private ModelMapper modelMapper;

    private final PlayersRepository repository;

    TransactionController(PlayersRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/transactions")
    public Set<Transaction> getTransactionsOfUser(@RequestParam(value = "id", defaultValue = "1") long playerId)
            throws PlayerException {
        Optional<Player> player = repository.findById(playerId);
        if (player.isPresent()) {
            return player.get().getTransactions();
        } else {
            throw new PlayerException("Player not found");
        }
    }

    @PostMapping("/transaction")
    public Transaction addTransaction(@RequestBody TransactionDTO transactionDTO)
            throws TransactionException, PlayerException {
        Optional<Player> player = repository.findById(transactionDTO.getPlayerId());
        Transaction transaction = createFromDto(transactionDTO);
        if (player.isPresent()) {
            Player playerReceived = player.get();
            transaction.setPlayer(playerReceived);
            playerReceived.addTransaction(transaction);
            repository.save(playerReceived);
        } else {
            throw new PlayerException("Player not found");
        }
        return transaction;
    }

    private Transaction createFromDto(TransactionDTO transactionDTO) throws TransactionException {
        return new Transaction(transactionDTO.getTransactionId(),
                transactionDTO.getTransactionType(),
                transactionDTO.getAmount());
    }

    // Single item

//        @GetMapping("/employees/{id}")
//        Employee one(@PathVariable Long id) {
//
//                return repository.findById(id)
//                        .orElseThrow(() -> new EmployeeNotFoundException(id));
//        }

//        @PutMapping("/employees/{id}")
//        Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
//
//                return repository.findById(id)
//                        .map(employee -> {
//                                employee.setName(newEmployee.getName());
//                                employee.setRole(newEmployee.getRole());
//                                return repository.save(employee);
//                        })
//                        .orElseGet(() -> {
//                                newEmployee.setId(id);
//                                return repository.save(newEmployee);
//                        });
//        }

//        @DeleteMapping("/employees/{id}")
//        void deleteEmployee(@PathVariable Long id) {
//                repository.deleteById(id);
//        }
}