package com.dragons_of_mugloar.service;

import com.dragons_of_mugloar.model.Game;
import com.dragons_of_mugloar.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameLoopService {

    private GameService gameService;
    private MessageService messageService;

    public GameLoopService(GameService gameService, MessageService messageService) {
        this.gameService = gameService;
        this.messageService = messageService;
    }

    public void playGame() {
        Game game = gameService.startGame();
        System.out.println("Game started: " + game.getGameId());

        while (game.getLives() > 0) {
            List<Message> messages = messageService.getMessages(game.getGameId());

            if (messages == null || messages.isEmpty()) {
                System.out.println("No messages found");
                return;
            }

            Message firstMessage = messages.getFirst();
            System.out.println("Trying to complete: " + firstMessage.getMessage());

            String result = messageService.solveMessage(game.getGameId(), firstMessage.getAdId());
            System.out.println("Result: " + result);
        }
        System.out.print("Game over. Final score: " + game.getScore());
    }
}
