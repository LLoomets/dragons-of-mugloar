package com.dragons_of_mugloar.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolvedMessage {
    private boolean success;
    private int lives;
    private int gold;
    private int score;
    private int highScore;
    private int turn;
    private String message;
}
