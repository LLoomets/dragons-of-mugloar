package com.dragons_of_mugloar.controller;

import com.dragons_of_mugloar.model.Game;
import com.dragons_of_mugloar.model.Message;
import com.dragons_of_mugloar.service.GameService;
import com.dragons_of_mugloar.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    private GameService gameService;
    private MessageService messageService;

    public GameController(GameService gameService, MessageService messageService) {
        this.gameService = gameService;
        this.messageService = messageService;
    }

    @GetMapping("/start")
    public Game startGame() {
        return gameService.startGame();
    }

    @GetMapping("/{gameId}/messages")
    public List<Message> getMessages(@PathVariable String gameId) {
        return messageService.getMessages(gameId);
    }

    @GetMapping("/{gameId}/solve/{adId}")
    public String solveMessage(@PathVariable String gameId, @PathVariable String adId) {
        return messageService.solveMessage(gameId, adId);
    }
}
