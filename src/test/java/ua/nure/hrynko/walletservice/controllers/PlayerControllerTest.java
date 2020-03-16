package ua.nure.hrynko.walletservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ua.nure.hrynko.walletservice.entities.Player;
import ua.nure.hrynko.walletservice.repositories.PlayersRepository;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTest {
    @Autowired
    private PlayersRepository playersRepository;

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
}
