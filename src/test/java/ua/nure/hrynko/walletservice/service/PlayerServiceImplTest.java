package ua.nure.hrynko.walletservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.nure.hrynko.walletservice.dto.TransactionDTO;
import ua.nure.hrynko.walletservice.entities.Player;
import ua.nure.hrynko.walletservice.entities.Transaction;
import ua.nure.hrynko.walletservice.enums.TransactionType;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;
import ua.nure.hrynko.walletservice.repositories.PlayersRepository;
import ua.nure.hrynko.walletservice.repositories.TransactionRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PlayerServiceImplTest {
    private PlayerService playerService;

    @Mock
    PlayersRepository playersRepository;

    @Mock
    TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        playerService = new PlayerServiceImpl(playersRepository, transactionRepository);
    }

    @Test
    void getAllPlayers() {
        List<Player> players = new ArrayList<>();
        Player player = new Player("A", "B", 0);
        players.add(player);

        when(playersRepository.findAll()).thenReturn(players);

        assertEquals((List<Player>) playerService.getAllPlayers().getBody(), players);
        //Verifying that method is called once
        verify(playersRepository, times(1)).findAll();
    }

    @Test
    void getPlayerById() {
        //given
        Player player = new Player("A", "B", 0);
        when(playersRepository.findById(1L)).thenReturn(java.util.Optional.of(player));

        assertEquals(playerService.getPlayerById(1).getBody(), java.util.Optional.of(player));
        //Verifying that method is called once
        verify(playersRepository, times(1)).findById(1L);
    }

    @Test
    void getAllTransactionsOfPlayer() {
        //given
        Transaction transaction = new Transaction("dded5454cfcfh6676", TransactionType.CREDIT, 5.0);
        Set<Transaction> transactions = new HashSet<>();
        transactions.add(transaction);

        when(transactionRepository.findByPlayerId(1L)).thenReturn(transactions);

        assertEquals(playerService.getAllTransactionsOfPlayer(1L).getBody(), transactions);
        //Verifying that method is called once
        verify(transactionRepository, times(1)).findByPlayerId(1L);
    }

    /**
     * Unit test of limited scope to check if save method is working in general
     * Checking if method is
     */
    @Test
    void addTransaction() throws TransactionException {
        // given
        Transaction transaction = new Transaction("", TransactionType.CREDIT, 0);
        TransactionDTO transactionDTO = new TransactionDTO(transaction.getTransactionId(),
                1, transaction.getTransactionType(), transaction.getAmount());

        Player player = new Player("A", "B", 0);
        when(playersRepository.findById(1L)).thenReturn(java.util.Optional.of(player));

        //when
        playerService.addTransaction(transactionDTO);

        //then
        //checking that save method is called
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    void addTransactionError() {
        // given
        Player player = new Player("A", "B", 0);
        when(playersRepository.findById(1L)).thenReturn(java.util.Optional.of(player));
        TransactionDTO transactionDebitDTO = new TransactionDTO("", 1L, TransactionType.DEBIT, 100);

        //player balance should be checked
        assertThrows(TransactionException.class, () -> {
            playerService.addTransaction(transactionDebitDTO);
        });
    }
}