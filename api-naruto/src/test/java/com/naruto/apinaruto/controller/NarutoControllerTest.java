package com.naruto.apinaruto.controller;

import com.naruto.apinaruto.model.NarutoModel;
import com.naruto.apinaruto.repository.NarutoRepository;
import com.naruto.apinaruto.service.NarutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(NarutoController.class)
@AutoConfigureMockMvc
public class NarutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NarutoRepository narutoRepository;

    @MockBean
    private NarutoService narutoService;

    private NarutoModel narutoModel;

    @BeforeEach
    public void setUp() {
        narutoModel = new NarutoModel(1L,
                "Naruto Uzumaki",
                List.of("image1.png"),
                Map.of("father", "Minato"),
                List.of("Rasengan"));
    }

    @Test
    public void testSearchByName() throws Exception {
        given(narutoService.searchByName(anyString())).willReturn(List.of(narutoModel));
        //Simula uma requisição GET
        mockMvc.perform(get("/ninjas/characters")
                        .param("name", "Naruto")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Naruto Uzumaki"));
    }

    @Test
    public void testSave() throws Exception {
        given(narutoService.searchByName(anyString())).willReturn(List.of(narutoModel));
        given(narutoRepository.save(narutoModel)).willReturn(narutoModel);
        //Simula uma requisição POST
        mockMvc.perform(post("/ninjas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Naruto Uzumaki\", \"images\": [\"image1.png\"], \"family\": {\"father\": \"Minato\"}, \"jutsu\": [\"Rasengan\"] }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Naruto Uzumaki"));
    }

    @Test
    public void testListAll() throws Exception {
        given(narutoRepository.findAll()).willReturn(List.of(narutoModel));
        //Simula uma requisição GET para listar todos
        mockMvc.perform(get("/ninjas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Naruto Uzumaki"));
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(narutoRepository).deleteById(1L);
        //Simula uma requisição DELETE
        mockMvc.perform(delete("/ninjas/characters/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
