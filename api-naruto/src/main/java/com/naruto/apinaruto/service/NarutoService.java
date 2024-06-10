package com.naruto.apinaruto.service;

import com.naruto.apinaruto.model.NarutoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class NarutoService {

    private static final Logger logger = LoggerFactory.getLogger(NarutoService.class);

    private final RestTemplate restTemplate = new RestTemplate();

    public static class ApiResponse {

        public List<NarutoModel> characters;
    }

    public List<NarutoModel> searchByName(String name) {

        String url = "https://dattebayo-api.onrender.com/characters";

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", name)
                .toUriString();

        logger.info("Searching for character by name: {}", name);

        logger.info("Request URL: {}", urlTemplate);

        try {
            ApiResponse response = restTemplate.getForObject(urlTemplate, ApiResponse.class);

            if (response != null && response.characters != null && !response.characters.isEmpty()) {
                NarutoModel character = response.characters.get(0);

                logger.info("Received response for character: {}", character.getName());

                return List.of(character);

            } else {
                logger.warn("API response is null or empty");
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching data from API: {}", e.getMessage());
        }
        return new ArrayList<>();
    }
}
