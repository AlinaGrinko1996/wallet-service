package ua.nure.hrynko.walletservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.hrynko.walletservice.entities.Player;
import ua.nure.hrynko.walletservice.repositories.PlayersRepository;

import java.util.List;

@RestController
public class PlayersController {
    @Autowired
    private PlayersRepository playersRepository;

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playersRepository.findAll();
    }
}
