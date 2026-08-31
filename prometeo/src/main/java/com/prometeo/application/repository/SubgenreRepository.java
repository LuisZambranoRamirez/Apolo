package com.prometeo.application.repository;

import com.prometeo.application.entity.Subgenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubgenreRepository extends JpaRepository<Subgenre, String> {
}

