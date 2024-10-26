package com.naruto.apinaruto.repository;

import com.naruto.apinaruto.model.NarutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface de repositório que estende JpaRepository, fornecendo métodos CRUD para NarutoModel

public interface NarutoRepository extends JpaRepository<NarutoModel, Long> {
    // Nenhum método adicional é necessário aqui,
    // pois JpaRepository já oferece métodos como save, findAll, findById, delete, etc
}
