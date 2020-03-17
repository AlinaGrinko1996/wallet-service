package ua.nure.hrynko.walletservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.nure.hrynko.walletservice.dto.TransactionDTO;
import ua.nure.hrynko.walletservice.entities.Player;
import ua.nure.hrynko.walletservice.entities.Transaction;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;
import ua.nure.hrynko.walletservice.repositories.PlayersRepository;
import ua.nure.hrynko.walletservice.repositories.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayersRepository playersRepository;

    private TransactionRepository transactionRepository;

    public PlayerServiceImpl(PlayersRepository playersRepository, TransactionRepository transactionRepository) {
        this.playersRepository = playersRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Gets all players in repository
     * @return ResponseEntity<List<Players>> or HttpStatus.NOT_FOUND if there are no players
     */
    @Override
    public ResponseEntity<?> getAllPlayers() {
        List<Player> players = playersRepository.findAll();

        if (players.isEmpty()) {
            return new ResponseEntity<>("No Players Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    /**
     * Return information (including balance) about player by his/her id
     * @param playerId id number of player
     * @return ResponseEntity<Player> or HttpStatus.NOT_FOUND
     */
    @Override
    public ResponseEntity<?> getPlayerById(long playerId) {
        Optional<Player> player = playersRepository.findById(playerId);
        if (!player.isPresent()) {
            return new ResponseEntity<>(String.format("No players with id %d were found", playerId),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.FOUND);
    }

    /**
     * Gets transaction history of player.
     *
     * There is no check for "not existing players.
     * Potentially there could be a need to refund deleted users' money or similar - so it seemed unnecessary.
     * @param playerId id of player
     * @return ResponseEntity<Transaction> if some were found or HttpStatus.NOT_FOUND if not
     */
    @Override
    public ResponseEntity<?> getAllTransactionsOfPlayer(long playerId) {
        Set<Transaction> transactions = transactionRepository.findByPlayerId(playerId);

        if (transactions.isEmpty()) {
            return new ResponseEntity<>("No Transactions Found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    /**
     * Method to register users transaction.
     * Can be moved to separate service if there would be more then one method with "transaction-related" functionality.
     *
     * @param transactionDTO DTO used to collect information from client.
     * @return ResponseEntity<Transaction> if transaction was added or HttpStatus.NOT_FOUND if player's id is wrong.
     * @throws TransactionException in case if transaction with same id already exists,
     * or if user doesn't have enough balance.
     */
    @Override
    public ResponseEntity<?> addTransaction(TransactionDTO transactionDTO) throws TransactionException {
        Optional<Player> player = playersRepository.findById(transactionDTO.getPlayerId());

        if (!player.isPresent()) {
            return new ResponseEntity<>("No Players Found", HttpStatus.NOT_FOUND);
        }

        if (transactionRepository.existsById(transactionDTO.getTransactionId())) {
            log.error(String.format("Duplicated transaction; id = %s", transactionDTO.getTransactionId()));
            throw new TransactionException("Duplicated transaction!");
        }

        Transaction newTransaction = createFromDto(transactionDTO);
        newTransaction.setPlayer(player.get());

        playersRepository.save(player.get());
        return new ResponseEntity<>(transactionRepository.save(newTransaction), HttpStatus.CREATED);
    }

    private Transaction createFromDto(TransactionDTO transactionDTO) {
        return new Transaction(transactionDTO.getTransactionId(),
                transactionDTO.getTransactionType(),
                transactionDTO.getAmount());
    }
}
