package com.naruto.apinaruto.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

// A anotação @Entity indica que essa classe será mapeada para uma tabela no banco de dados
@Entity
public class NarutoModel {
    // Anotação @Id define o campo 'id' como a chave primária da tabela
    @Id
    private Long id;
    // Campo representando o nome do personagem
    private String name;
    // Anotação @ElementCollection indica que 'images' será mapeado para uma tabela separada, contendo uma lista de strings
    @ElementCollection
    private List<String> images;
    // Anotação @ElementCollection para 'family', que é um mapa de chave-valor, onde ambos são strings
    @ElementCollection
    private Map<String, String> family;
    // Anotação @ElementCollection para 'jutsu', que é uma lista de strings representando os jutsus do personagem
    @ElementCollection
    private List<String> jutsu;
    // Construtor padrão sem argumentos (necessário para o JPA)
    public NarutoModel() {
    }
    // Construtor com todos os argumentos para inicializar os atributos
    public NarutoModel(Long id, String name, List<String> images, Map<String, String> family, List<String> jutsu) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.family = family;
        this.jutsu = jutsu;
    }
    // Métodos getter e setter para acessar e modificar os atributos da classe
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
    public Map<String, String> getFamily() {
        return family;
    }
    public void setFamily(Map<String, String> family) {
        this.family = family;
    }
    public List<String> getJutsu() {
        return jutsu;
    }
    public void setJutsu(List<String> jutsu) {
        this.jutsu = jutsu;
    }
}
