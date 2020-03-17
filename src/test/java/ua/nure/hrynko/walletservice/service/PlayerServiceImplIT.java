package ua.nure.hrynko.walletservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.nure.hrynko.walletservice.dto.TransactionDTO;
import ua.nure.hrynko.walletservice.entities.Player;
import ua.nure.hrynko.walletservice.entities.Transaction;
import ua.nure.hrynko.walletservice.enums.TransactionType;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;
import ua.nure.hrynko.walletservice.repositories.PlayersRepository;
import ua.nure.hrynko.walletservice.repositories.TransactionRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class PlayerServiceImplIT {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PlayerService playerService;

    /**
     * Checking if transactions are really inserted normally
     */
    @Test
    void addTransaction() throws TransactionException {
        //given
        Transaction transaction = new Transaction("123", TransactionType.CREDIT, 0);
        TransactionDTO transactionDTO = new TransactionDTO(transaction.getTransactionId(),
                1, transaction.getTransactionType(), transaction.getAmount());

        //when
        playerService.addTransaction(transactionDTO);

        //then
        assertEquals(transactionRepository.findById("123"), java.util.Optional.of(transaction));
    }

    /**
     * Asserting equal transactions
     * @throws TransactionException if second transaction with the same id was inserted
     */
    @Test
    void addTransactionError() throws TransactionException {
        //given
        TransactionDTO transactionDTO1 = new TransactionDTO("321", 1, TransactionType.CREDIT, 0);
        TransactionDTO transactionDTO2 = new TransactionDTO("321", 1, TransactionType.CREDIT, 0);

        //when
        playerService.addTransaction(transactionDTO1);

        //then
        assertThrows(TransactionException.class, () -> {
            playerService.addTransaction(transactionDTO2);
        });
    }
}
