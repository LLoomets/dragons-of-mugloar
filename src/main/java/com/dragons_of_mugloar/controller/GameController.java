package com.dragons_of_mugloar.controller;

import com.dragons_of_mugloar.model.Game;
import com.dragons_of_mugloar.model.Message;
import com.dragons_of_mugloar.service.GameLoopService;
import com.dragons_of_mugloar.service.GameService;
import com.dragons_of_mugloar.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
