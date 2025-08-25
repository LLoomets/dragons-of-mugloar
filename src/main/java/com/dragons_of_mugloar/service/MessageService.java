package com.dragons_of_mugloar.service;

import com.dragons_of_mugloar.model.Message;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class MessageService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseURL = "https://dragonsofmugloar.com/api/v2";

    public List<Message> getMessages(String gameId) {
        String url = baseURL +"/" + gameId + "/messages";
        ResponseEntity<List<Message>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
        return response.getBody();
    }

    public String solveMessage(String gameId, String adId) {
        String url = baseURL + "/" + gameId + "/solve/" + adId;
        return restTemplate.postForObject(url, null, String.class);
    }
}
