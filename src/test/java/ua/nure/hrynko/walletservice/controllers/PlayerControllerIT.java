package ua.nure.hrynko.walletservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ua.nure.hrynko.walletservice.entities.Player;
import ua.nure.hrynko.walletservice.entities.Transaction;
import ua.nure.hrynko.walletservice.repositories.PlayersRepository;
import ua.nure.hrynko.walletservice.repositories.TransactionRepository;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerIT {
    @Autowired
    private PlayersRepository playersRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllPlayersTest() throws Exception {
        List<Player> players = playersRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(players);

        this.mockMvc.perform(get("/players"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(jsonString)));
    }

    @Test
    void getPlayerByIdTest() throws Exception {
        Long id = 1L;
        Optional<Player> player = playersRepository.findById(id);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(player.get());

        this.mockMvc.perform(get("/player/" + id))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().string(containsString(jsonString)));
    }

    @Test
    void getTransactionsOfPlayerTest() throws Exception {
        long id = 1L;
        Set<Transaction> transactions = transactionRepository.findByPlayerId(id);
        List<String> ids = new ArrayList<>();
        transactions.forEach(transaction -> ids.add(transaction.getTransactionId()));

        this.mockMvc.perform(get(String.format("/player/%d/transactions", id)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].transactionId", Matchers.containsString(ids.get(0))));
    }
}
