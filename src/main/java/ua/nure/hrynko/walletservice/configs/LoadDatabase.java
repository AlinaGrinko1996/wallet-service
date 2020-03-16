package ua.nure.hrynko.walletservice.configs;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.nure.hrynko.walletservice.entities.Player;
import ua.nure.hrynko.walletservice.entities.Transaction;
import ua.nure.hrynko.walletservice.enums.TransactionType;
import ua.nure.hrynko.walletservice.repositories.PlayersRepository;
import ua.nure.hrynko.walletservice.repositories.TransactionRepository;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(PlayersRepository playersRepository, TransactionRepository transactionRepository) {
        return args -> {
//            Player dorian = playersRepository.save(new Player("Dorian", "Gray", 100));
//            Player tom = playersRepository.save(new Player("Tom", "Sayer", 10));
//            transactionRepository.save(new Transaction("123ASD456ERT789", dorian, TransactionType.CREDIT, 70));
//            transactionRepository.save(new Transaction("987ASD456ERT789", dorian, TransactionType.DEBIT, 7));
//            log.info("Preload " + repository.save(new Player("Dorian", "Gray", 100)));
//            log.info("Preload" + repository.save(new Player("Tom", "Sayer", 10)));
        };
    }
}
