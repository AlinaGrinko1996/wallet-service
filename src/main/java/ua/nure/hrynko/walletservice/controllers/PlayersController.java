package ua.nure.hrynko.walletservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.hrynko.walletservice.entities.Player;
import ua.nure.hrynko.walletservice.exceptions.PlayerException;
import ua.nure.hrynko.walletservice.repositories.PlayersRepository;

import java.util.List;

@RestController
public class PlayersController {
    private PlayersRepository playersRepository;

    public PlayersController(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playersRepository.findAll();
    }

    @GetMapping("/player")
    public Player getPlayer(@RequestParam(value = "id", defaultValue = "1") long userId) throws PlayerException {
        return playersRepository
                .findById(userId)
                .orElseThrow(() -> new PlayerException("Wrong player id called!"));
    }
}
