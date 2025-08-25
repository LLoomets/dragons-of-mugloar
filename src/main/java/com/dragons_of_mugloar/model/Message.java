package com.dragons_of_mugloar.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String adId;
    private String message;
    private String reward;
    private int expiresIn;
}
