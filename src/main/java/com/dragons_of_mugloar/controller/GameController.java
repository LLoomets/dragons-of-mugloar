package com.dragons_of_mugloar.controller;

import com.dragons_of_mugloar.model.Game;
import com.dragons_of_mugloar.model.Message;
import com.dragons_of_mugloar.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/start")
    public Game startGame() {
        return gameService.startGame();
    }

    @GetMapping("/{gameId}/messages")
    public List<Message> getMessages(@PathVariable String gameId) {
        return gameService.getMessages(gameId);
    }

    @GetMapping("/{gameId}/solve/{adId}")
    public String solveMessage(@PathVariable String gameId, @PathVariable String adId) {
        return gameService.solveMessage(gameId, adId);
    }
}
