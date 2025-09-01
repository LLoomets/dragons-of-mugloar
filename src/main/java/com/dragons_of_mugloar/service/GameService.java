package com.dragons_of_mugloar.service;

import com.dragons_of_mugloar.model.Game;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GameService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseURL = "https://dragonsofmugloar.com/api/v2";

    public Game startGame() {
        String url = baseURL + "/game/start";
        return restTemplate.postForObject(url, null, Game.class);
    }
}
