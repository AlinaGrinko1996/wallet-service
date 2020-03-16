package ua.nure.hrynko.walletservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.nure.hrynko.walletservice.controllers.PlayersController;
import ua.nure.hrynko.walletservice.controllers.TransactionController;

@SpringBootTest
class SmokeTest {
    @Autowired
    private PlayersController playersController;

    @Autowired
    private TransactionController transactionController;

    @Test
    void contextLoads() {
        assertThat(playersController).isNotNull();
        assertThat(transactionController).isNotNull();
    }
}
