package ua.nure.hrynko.walletservice.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ua.nure.hrynko.walletservice.entities.Player;
import ua.nure.hrynko.walletservice.enums.TransactionType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

public class TransactionDTO {
    private String transactionId;
    private long playerId;
    private TransactionType transactionType;
    private double amount;

    public TransactionDTO(String transactionId, long playerId, TransactionType transactionType, double amount) {
        this.transactionId = transactionId;
        this.playerId = playerId;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
