package ua.nure.hrynko.walletservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.hrynko.walletservice.entities.Transaction;

import java.util.Set;

public interface TransactionRepository  extends JpaRepository<Transaction, String> {
    Set<Transaction> findByPlayerId(long playerId);
}
