package com.compass.repository;

import com.compass.model.Partido;
import com.compass.model.enums.Ideologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    List<Partido> findByIdeologia(Ideologia ideologia);
}
