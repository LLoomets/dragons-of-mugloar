package com.dragons_of_mugloar.service;

import com.dragons_of_mugloar.model.Message;
import com.dragons_of_mugloar.model.SolvedMessage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Comparator;
import java.util.List;

@Service
public class MessageService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseURL = "https://dragonsofmugloar.com/api/v2";

    public List<Message> getMessages(String gameId) {
        String url = baseURL +"/" + gameId + "/messages";
        ResponseEntity<List<Message>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    public SolvedMessage solveMessage(String gameId, String adId) {
        String url = baseURL + "/" + gameId + "/solve/" + adId;
        return restTemplate.postForObject(url, null, SolvedMessage.class);
    }

    public Message chooseSafeMessage(List<Message> messages) {
        int minRisk = messages.stream()
                .mapToInt(this::getRiskLevel)
                .min()
                .orElse(100);

        List<Message> safestMessage = messages.stream()
                .filter(m -> getRiskLevel(m) == minRisk)
                .toList();

        return safestMessage.stream()
                .max(Comparator.comparingInt(Message::getReward))
                .orElse(safestMessage.getFirst());

    }

    public int getRiskLevel(Message message) {
        if (message.getProbability() == null) return 100;

        return switch (message.getProbability()) {
            case "Sure thing" -> 1;
            case "Piece of cake" -> 5;
            case "Walk in the park" -> 10;
            case "Quite likely" -> 20;
            case "Hmmm...." -> 40;
            case "Gamble" -> 50;
            case "Risky" -> 60;
            case "Playing with fire" -> 70;
            case "Rather detrimental" -> 80;
            case "Impossible", "Suicide mission" -> 100;
            default -> 100;
        };
    }
}
