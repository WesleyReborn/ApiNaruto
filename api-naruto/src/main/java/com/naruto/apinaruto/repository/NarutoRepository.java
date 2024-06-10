package com.naruto.apinaruto.repository;

import com.naruto.apinaruto.model.NarutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NarutoRepository extends JpaRepository<NarutoModel, Long> {
}
