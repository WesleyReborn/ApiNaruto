package com.naruto.apinaruto.controller;

import com.naruto.apinaruto.model.NarutoModel;
import com.naruto.apinaruto.repository.NarutoRepository;
import com.naruto.apinaruto.service.NarutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/ninjas")
public class NarutoController {

    @Autowired
    private NarutoRepository narutoRepository;

    @Autowired
    private NarutoService narutoService;

    @GetMapping("/characters")
    public List<NarutoModel> searchByName(@RequestParam("name") String name) {
        return narutoService.searchByName(name);
    }

    @PostMapping
    public NarutoModel save(@RequestBody NarutoModel newCharacter) {
        List<NarutoModel> characters = narutoService.searchByName(newCharacter.getName().split(" ")[0]);
        if (!characters.isEmpty()) {
            NarutoModel exactCharacter = characters.get(0);
            return narutoRepository.save(exactCharacter);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Character not found"
            );
        }
    }

    @GetMapping
    public List<NarutoModel> listAll(){
        return narutoRepository.findAll();
    }

    @DeleteMapping("/characters/{id}")
    public void delete(@PathVariable("id") Long id) {
        narutoRepository.deleteById(id);
    }
}
