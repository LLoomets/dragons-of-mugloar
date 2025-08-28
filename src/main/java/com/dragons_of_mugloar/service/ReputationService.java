package com.dragons_of_mugloar.service;

import com.dragons_of_mugloar.model.Reputation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReputationService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseURL = "https://dragonsofmugloar.com/api/v2";

    public Reputation getReputation(String gameId) {
        String url = baseURL + "/" + gameId + "/investigate/reputation";
        return restTemplate.postForObject(url, null, Reputation.class);
    }
}
