package com.naruto.apinaruto.controller;

import com.naruto.apinaruto.model.NarutoModel;
import com.naruto.apinaruto.repository.NarutoRepository;
import com.naruto.apinaruto.service.NarutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
// Anotação @RestController define que essa classe é um controlador REST, que trata as requisições HTTP
@RestController
@RequestMapping("/ninjas") // Define o endpoint base para todas as rotas da classe
public class NarutoController {
    // Injeção de dependência do repositório e do serviço usando @Autowired
    @Autowired
    private NarutoRepository narutoRepository;
    @Autowired
    private NarutoService narutoService;
    // Endpoint GET para buscar personagens pelo nome
    @GetMapping("/characters")
    public List<NarutoModel> searchByName(@RequestParam("name") String name) {
        // Chama o serviço para buscar personagens com base no nome
        return narutoService.searchByName(name);
    }
    // Endpoint POST para salvar um personagem no banco de dados
    @PostMapping
    public NarutoModel save(@RequestBody NarutoModel newCharacter) {
        // Busca personagens pelo primeiro nome do personagem enviado na requisição
        List<NarutoModel> characters = narutoService.searchByName(newCharacter.getName().split(" ")[0]);
        // Verifica se algum personagem foi encontrado
        if (!characters.isEmpty()) {
            // Se encontrado, pega o primeiro da lista e salva no banco de dados
            NarutoModel exactCharacter = characters.get(0);
            return narutoRepository.save(exactCharacter);
        } else {
            // Se não encontrado, lança uma exceção com status HTTP 404 (NOT FOUND)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Character not found"
            );
        }
    }
    // Endpoint GET para listar todos os personagens no banco de dados
    @GetMapping
    public List<NarutoModel> listAll(){
        // Retorna todos os personagens armazenados no repositório
        return narutoRepository.findAll();
    }
    // Endpoint DELETE para deletar um personagem pelo ID
    @DeleteMapping("/characters/{id}")
    public void delete(@PathVariable("id") Long id) {
        // Deleta o personagem com o ID fornecido
        narutoRepository.deleteById(id);
    }
}
