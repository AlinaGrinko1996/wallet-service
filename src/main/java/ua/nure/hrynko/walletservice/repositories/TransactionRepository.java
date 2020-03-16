package ua.nure.hrynko.walletservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.hrynko.walletservice.entities.Transaction;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction, String> {
    List<Transaction> findByPlayerId(long playerId);
}
