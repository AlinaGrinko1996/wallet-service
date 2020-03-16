package ua.nure.hrynko.walletservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import ua.nure.hrynko.walletservice.enums.TransactionType;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Transaction {
    @Id
    @Column(unique = true)
    private String transactionId;
    @ManyToOne
    @JsonManagedReference
    private Player player;
    //it is very important to not keep our data stored in db as 1/2/3...
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;
    private double amount;

    Transaction() {}

    public Transaction(String transactionId, TransactionType transactionType, double amount)
            throws TransactionException {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public void setPlayer(Player player) throws TransactionException {
        player.addTransaction(this);
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId.equals(that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", player=" + player +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                '}';
    }
}
