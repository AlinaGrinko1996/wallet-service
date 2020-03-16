package ua.nure.hrynko.walletservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.hrynko.walletservice.entities.Player;


public interface PlayersRepository extends JpaRepository<Player, Long> {
}
