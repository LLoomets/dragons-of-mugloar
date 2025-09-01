package com.dragons_of_mugloar.controller;

import com.dragons_of_mugloar.service.GameLoopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    private GameLoopService gameLoopService;

    public GameController(GameLoopService gameLoopService) {
        this.gameLoopService = gameLoopService;
    }

    @GetMapping("/start")
    public String playGame() {
        new Thread(() -> gameLoopService.playGame()).start();
        return "Game started in background. Check logs for progress.";
    }
}
