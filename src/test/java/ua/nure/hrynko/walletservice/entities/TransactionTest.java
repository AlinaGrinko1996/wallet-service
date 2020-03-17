package ua.nure.hrynko.walletservice.entities;

import org.junit.jupiter.api.Test;
import ua.nure.hrynko.walletservice.enums.TransactionType;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {
    @Test
    void setPlayer() throws TransactionException {
        Transaction transaction = new Transaction("dded5454cfcfh6676", TransactionType.CREDIT, 5.0);
        transaction.setPlayer(new Player("", "", 10.0));

        assertEquals(15.0, transaction.getPlayer().getBalance());

        Player player = mock(Player.class);
        transaction.setPlayer(player);

        //Checking that add transaction method is invoked
        verify(player, times(1)).addTransaction(transaction);
    }

    @Test
    void testEquals() {
        Transaction transaction1 = new Transaction("dded5454cfcfh6676", TransactionType.CREDIT, 5.0);
        Transaction transaction2 = new Transaction("dded5454cfcfh6676", TransactionType.DEBIT, 50.0);

        assertEquals(transaction1, transaction2);
        Transaction transaction3 = new Transaction("fooooooooobaaaarrr", TransactionType.CREDIT, 5.0);

        assertNotEquals(transaction1, transaction3);
    }
}