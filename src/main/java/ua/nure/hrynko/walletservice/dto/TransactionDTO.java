package ua.nure.hrynko.walletservice.dto;
import ua.nure.hrynko.walletservice.enums.TransactionType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Used to interact with user.
 * DTO / POJO are recommended for this purpose.
 * Validation of parameters inside.
 */
public class TransactionDTO {
    @NotNull
    private String transactionId;
    @NotNull
    private long playerId;
    @NotNull
    private TransactionType transactionType;
    @Positive
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

    public long getPlayerId() {
        return playerId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }
}
