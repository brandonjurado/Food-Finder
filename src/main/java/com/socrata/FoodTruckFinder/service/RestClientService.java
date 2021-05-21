package com.socrata.FoodTruckFinder.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {
    private final RestTemplate restTemplate;

    RestClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T sendRequest(String url, Class<T> clazz) {
        return restTemplate.getForObject(url, clazz);
    }
}