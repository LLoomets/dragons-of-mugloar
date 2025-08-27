package com.dragons_of_mugloar.service;

import com.dragons_of_mugloar.model.Item;
import com.dragons_of_mugloar.model.PurchaseResult;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ItemService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseURL = "https://dragonsofmugloar.com/api/v2";

    public List<Item> getItems(String gameId) {
        String url = baseURL + "/" + gameId + "/shop";
        ResponseEntity<List<Item>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    public PurchaseResult buyItem(String gameId, String itemId) {
        String url = baseURL + "/" + gameId + "/shop/buy/" + itemId;
        return restTemplate.postForObject(url, null, PurchaseResult.class);
    }
}
