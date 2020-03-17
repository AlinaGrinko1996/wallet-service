package ua.nure.hrynko.walletservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.nure.hrynko.walletservice.dto.TransactionDTO;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;

@Service
public interface PlayerService {
    /**
     * Gets all players in repository
     * @return ResponseEntity<List<Players>> or HttpStatus.NOT_FOUND if there are no players
     */
    ResponseEntity<?> getAllPlayers();
    /**
     * Return information (including balance) about player by his/her id
     * @param playerId id number of player
     * @return ResponseEntity<Player> or HttpStatus.NOT_FOUND
     */
    ResponseEntity<?> getPlayerById(long playerId);
    /**
     * Gets transaction history of player.
     *
     * There is no check for "not existing players.
     * Potentially there could be a need to refund deleted users' money or similar - so it seemed unnecessary.
     * @param playerId id of player
     * @return ResponseEntity<Transaction> if some were found or HttpStatus.NOT_FOUND if not
     */
    ResponseEntity<?> getAllTransactionsOfPlayer(long playerId);
    /**
     * Method to register users transaction.
     * Can be moved to separate service if there would be more then one method with "transaction-related" functionality.
     *
     * @param transactionDTO DTO used to collect information from client.
     * @return ResponseEntity<Transaction> if transaction was added or HttpStatus.NOT_FOUND if player's id is wrong.
     * @throws TransactionException in case if transaction with same id already exists,
     * or if user doesn't have enough balance.
     */
    ResponseEntity<?> addTransaction(TransactionDTO transactionDTO) throws TransactionException;
}
