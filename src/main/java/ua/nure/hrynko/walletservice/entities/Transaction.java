package ua.nure.hrynko.walletservice.entities;

import lombok.Data;
import ua.nure.hrynko.walletservice.enums.TransactionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Transaction {
    @Id
    @Column(unique = true)
    private String transactionId;
    @ManyToOne
    private Player player;
    private TransactionType transactionType;
    private double amount;

    Transaction() {}

    public Transaction(String transactionId, Player player, TransactionType transactionType, double amount) {
        this.transactionId = transactionId;
        this.player = player;
        this.transactionType = transactionType;
        this.amount = amount;
    }
}
