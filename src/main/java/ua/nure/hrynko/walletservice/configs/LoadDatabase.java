package ua.nure.hrynko.walletservice.configs;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.nure.hrynko.walletservice.entities.Player;
import ua.nure.hrynko.walletservice.repositories.PlayersRepository;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(PlayersRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Player("Dorian", "Gray", 100)));
            log.info("Preloading " + repository.save(new Player("Tom", "Sayer", 10)));
        };
    }
}
