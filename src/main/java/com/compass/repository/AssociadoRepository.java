package com.compass.repository;

import com.compass.model.Associado;
import com.compass.model.enums.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    List<Associado> findByCargo(Cargo cargo);



}
