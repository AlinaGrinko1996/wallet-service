package ua.nure.hrynko.walletservice.entities;

import org.junit.jupiter.api.Test;
import ua.nure.hrynko.walletservice.enums.TransactionType;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void addTransaction() throws TransactionException {
        Player player1 = new Player("A", "B", 0);
        player1.addTransaction(new Transaction("", TransactionType.CREDIT, 1));

        assertEquals(1, player1.getBalance());
    }

    @Test
    void addTransactionEdge() throws TransactionException {
        Player player1 = new Player("A", "B", 1);
        player1.addTransaction(new Transaction("", TransactionType.DEBIT, 1));

        assertEquals(0, player1.getBalance());
    }

    @Test
    void addTransactionError() {
        assertThrows(TransactionException.class, () -> {
            Player player1 = new Player("A", "B", 1);
            player1.addTransaction(new Transaction("", TransactionType.DEBIT, 2));
        });
    }

    @Test
    void testEquals() {
        Player player1 = new Player("A", "B", 0);
        Player player2 = new Player("A", "B", 0);

        Long id = 1L;
        player1.setId(id);
        player2.setId(id);

        assertEquals(player1, player2);

        player2.setId(2L);
        assertNotEquals(player1, player2);
    }
}