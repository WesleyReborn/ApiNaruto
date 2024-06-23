package com.naruto.apinaruto.service;

import com.naruto.apinaruto.model.NarutoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class NarutoServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NarutoService narutoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchByName() {
        // Retorna um personagem
        NarutoService.ApiResponse mockResponse = new NarutoService.ApiResponse();
        List<NarutoModel> characters = new ArrayList<>();
        characters.add(new NarutoModel(1L, "Naruto Uzumaki",null,null,null));
        mockResponse.characters = characters;

        when(restTemplate.getForObject(anyString(), any())).thenReturn(mockResponse);

        List<NarutoModel> result = narutoService.searchByName("Naruto");

        assertEquals(1, result.size());
        assertEquals("Naruto Uzumaki", result.get(0).getName());
    }

    @Test
    public void testSearchByNameEmptyList() {
        // Retornando uma lista vazia
        NarutoService.ApiResponse mockResponse = new NarutoService.ApiResponse();
        mockResponse.characters = new ArrayList<>();

        when(restTemplate.getForObject(anyString(), any())).thenReturn(mockResponse);

        List<NarutoModel> result = narutoService.searchByName("Unknown");

        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchByNameApiResponseIsNull() {
        // Retornando null
        when(restTemplate.getForObject(anyString(), any())).thenReturn(null);

        List<NarutoModel> result = narutoService.searchByName("Naruto Uzumaki");

        assertTrue(result.isEmpty(), "Expected result to be an empty list");
    }
}
