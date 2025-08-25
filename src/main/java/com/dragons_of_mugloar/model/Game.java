package com.dragons_of_mugloar.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    private String gameId;
    private int lives;
    private int gold;
    private int level;
    private int score;
    private int highScore;
    private int turn;

}
