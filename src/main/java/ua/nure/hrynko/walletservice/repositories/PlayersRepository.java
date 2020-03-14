package ua.nure.hrynko.walletservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.hrynko.walletservice.entities.Player;

import java.util.List;

public interface PlayersRepository extends JpaRepository<Player, Long> {

    List<Player> getById(Long userId);
}
