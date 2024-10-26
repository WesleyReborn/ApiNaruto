package com.naruto.apinaruto.service;

import com.naruto.apinaruto.exception.ApiException;
import com.naruto.apinaruto.model.NarutoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

// A anotação @Service indica que essa classe é um componente de serviço do Spring
@Service
public class NarutoService {
    // Logger para registrar mensagens de log (informações, alertas, erros)
    private static final Logger logger = LoggerFactory.getLogger(NarutoService.class);
    // RestTemplate é utilizado para fazer requisições HTTP (chamar APIs externas)
    private final RestTemplate restTemplate = new RestTemplate();
    // Classe que mapeia a resposta da API externa
    public static class ApiResponse {
        public List<NarutoModel> characters; // Lista de personagens retornados pela API
    }
    // Método que busca personagens pelo nome na API
    public List<NarutoModel> searchByName(String name) {
        // URL da API para buscar os personagens
        String url = "https://dattebayo-api.onrender.com/characters";
        // Constrói a URL com o parâmetro de consulta (query parameter)
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", name) // Adiciona o parâmetro 'name' à URL
                .toUriString(); // Gera a URL final com o parâmetro
        // Log informando que a busca pelo personagem foi iniciada
        logger.info("Searching for character by name: {}", name);
        // Log com a URL completa gerada
        logger.info("Request URL: {}", urlTemplate);
        try {
            // Faz a requisição GET para a API e recebe a resposta mapeada para ApiResponse
            ApiResponse response = restTemplate.getForObject(urlTemplate, ApiResponse.class);
            // Verifica se a resposta e a lista de personagens não são nulas e se há personagens na lista
            if (response != null && response.characters != null && !response.characters.isEmpty()) {
                // Pega o primeiro personagem da lista
                NarutoModel character = response.characters.get(0);
                // Log com o nome do personagem retornado pela API
                logger.info("Received response for character: {}", character.getName());
                // Retorna o personagem em uma lista
                return List.of(character);
            } else {
                // Log caso não tenha encontrado nenhum personagem com o nome informado
                logger.warn("No characters found for name: {}", name);
                // Retorna uma lista vazia se não encontrar personagens
                return new ArrayList<>();
            }
        } catch (Exception e) {
            // Log de erro se ocorrer alguma exceção durante a chamada da API
            logger.error("Error occurred while fetching data from API: {}", e.getMessage());
            // Lança uma exceção personalizada para ser tratada em outra parte da aplicação
            throw new ApiException("Failed to fetch data from API", e);
        }
    }
}
