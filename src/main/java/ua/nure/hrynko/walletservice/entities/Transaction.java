package ua.nure.hrynko.walletservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ua.nure.hrynko.walletservice.enums.TransactionType;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Transaction data
 * String type used for transactionId as usually it is numbers and characters [A-Za-z0-9]
 */
@Data
@Entity
@EqualsAndHashCode(of = {"transactionId"})
public class Transaction {
    @Id
    @Column(unique = true)
    private String transactionId;
    @ManyToOne
    @JsonIgnore
    private Player player;
    /**
     * Enum type STRING used to not store enum in DB as numbers
     */
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    private double amount;
    @CreationTimestamp
    private LocalDateTime transactionDateTime;

    public Transaction() {}

    public Transaction(String transactionId, TransactionType transactionType, double amount) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    /**
     * Setter of player
     * Needed to make sure that users balance will change when transaction is registered
     * @param player player object
     * @throws TransactionException in case if balance is not enough
     */
    public void setPlayer(Player player) throws TransactionException {
        player.addTransaction(this);
        this.player = player;
    }
}
