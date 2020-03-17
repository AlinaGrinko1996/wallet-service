package ua.nure.hrynko.walletservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.hrynko.walletservice.service.PlayerService;

@RestController
public class PlayersController {
    private PlayerService playerService;

    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public ResponseEntity<?> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    /**
     * Gets user data (including balance) by id
     * @param userId id of player
     * @return ResponseEntity<Player>> or NOT_FOUND
     */
    @GetMapping("/player/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable("id") long userId) {
        return playerService.getPlayerById(userId);
    }

    /**
     * Gets players transactions
     * @param playerId id passed in url
     * @return ResponseEntity<?> of transactions or NOT_FOUND
     */
    @GetMapping("/player/{id}/transactions")
    public ResponseEntity<?> getTransactionsOfPlayer(@PathVariable("id") long playerId) {
        return playerService.getAllTransactionsOfPlayer(playerId);
    }
}
