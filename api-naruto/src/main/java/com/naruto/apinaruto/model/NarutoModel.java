package com.naruto.apinaruto.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
public class NarutoModel {

    @Id
    private Long id;

    private String name;

    @ElementCollection
    private List<String> images;

    @ElementCollection
    private Map<String, String> family;

    @ElementCollection
    private List<String> jutsu;

    public NarutoModel() {
    }

    public NarutoModel(Long id, String name, List<String> images, Map<String, String> family, List<String> jutsu) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.family = family;
        this.jutsu = jutsu;
    }

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
