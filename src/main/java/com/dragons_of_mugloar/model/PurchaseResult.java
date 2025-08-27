package com.dragons_of_mugloar.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseResult {
    private boolean shoppingSuccess;
    private int gold;
    private int lives;
    private int level;
    private int turn;
}
