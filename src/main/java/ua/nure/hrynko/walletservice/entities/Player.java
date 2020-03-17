package ua.nure.hrynko.walletservice.entities;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import ua.nure.hrynko.walletservice.enums.TransactionType;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;

import javax.persistence.*;

/**
 * Class with general information about player
 * Some additional information can be added depending on future requirements.
 *
 * I don't store set of transactions here.
 * Having bidirectional One-to-Many Many-to-One was recommended in the sources I read,
 * however this set can potentially be huge, so that seemed unnecessary.
 */
@Data
@Slf4j
@Entity
@EqualsAndHashCode(of = {"id"})
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private double balance;

    Player() {}

    public Player(String firstName, String lastName, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    /**
     * Changes balance when user registers transactions.
     * Needed to provide atomicity of changing balance + registering transaction operation
     * @param transaction Transaction to be inserted
     * @throws TransactionException in case if there ia not enough money for current transaction
     */
    void addTransaction(Transaction transaction) throws TransactionException {
        if (transaction.getTransactionType() == TransactionType.CREDIT) {
            this.balance += transaction.getAmount();
        } else if (this.balance >= transaction.getAmount()) {
            this.balance -= transaction.getAmount();
        } else {
            log.error(String.format("Balance is not enough. Player id %d", id));
            throw new TransactionException("You balance is not enough!");
        }
    }
}
