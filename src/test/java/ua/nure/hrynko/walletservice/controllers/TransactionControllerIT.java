package ua.nure.hrynko.walletservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.nure.hrynko.walletservice.dto.TransactionDTO;
import ua.nure.hrynko.walletservice.enums.TransactionType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerIT {
    @Autowired
    private MockMvc mvc;

    @Test
    void addTransaction() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO("trfcjf55ftrtr6565", 2, TransactionType.CREDIT, 4);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(transactionDTO);
        mvc.perform(MockMvcRequestBuilders
                .post("/transaction")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.transactionId").exists());
    }
}
