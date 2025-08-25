package com.dragons_of_mugloar.controller;

import com.dragons_of_mugloar.model.Game;
import com.dragons_of_mugloar.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
