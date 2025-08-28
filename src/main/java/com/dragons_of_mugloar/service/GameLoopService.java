package com.dragons_of_mugloar.service;

import com.dragons_of_mugloar.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class GameLoopService {

    private GameService gameService;
    private MessageService messageService;
    private ItemService itemService;
    private ReputationService reputationService;

    public GameLoopService(GameService gameService, MessageService messageService, ItemService itemService, ReputationService reputationService) {
        this.gameService = gameService;
        this.messageService = messageService;
        this.itemService = itemService;
        this.reputationService = reputationService;
    }

    public void playGame() {
        Game game = gameService.startGame();
        System.out.println("Game started: " + game.getGameId());

        while (game.getLives() > 0) {
            try {
                List<Message> messages = messageService.getMessages(game.getGameId());

                if (messages == null || messages.isEmpty()) {
                    System.out.println("No messages found");
                    return;
                }

                if (game.getLives() < 3 && game.getGold() >= 50) {
                    System.out.println("Buying healing potion...");
                    PurchaseResult purchase = itemService.buyItem(game.getGameId(), "hpot");

                    System.out.println("Purchase success: " + purchase.isShoppingSuccess() + ", gold left: " + purchase.getGold());
                    game.setGold(purchase.getGold());
                    game.setLives(purchase.getLives());

                    messages = messageService.getMessages(game.getGameId());
                }

                Message chosenMessage = messageService.chooseSafeMessage(messages);
                int riskLevel = messageService.getRiskLevel(chosenMessage);
                int rewardValue = chosenMessage.getReward();
                System.out.println("Trying to complete: " + chosenMessage.getMessage() + " (risk: " + riskLevel + ", reward: " + rewardValue + ")");

                SolvedMessage result = messageService.solveMessage(game.getGameId(), chosenMessage.getAdId());
                System.out.printf("Result: success=%b, lives=%d, gold=%d, score=%d, highScore=%d, turn=%d, message=%s%n",
                        result.isSuccess(), result.getLives(), result.getGold(), result.getScore(), result.getHighScore(), result.getTurn(), result.getMessage());

                Reputation reputation = reputationService.getReputation(game.getGameId());
                System.out.printf("Current reputation: people=%d, state=%d, underworld=%d%n", reputation.getPeople(), reputation.getState(), reputation.getUnderworld());
                System.out.println(" ");

                game.setScore(result.getScore());
                game.setHighScore(result.getHighScore());
                game.setLives(result.getLives());
                game.setGold(result.getGold());

            } catch (Exception e) {
                System.out.println("Game over. Final score: " + game.getScore());
                break;
            }
        }
    }
}
