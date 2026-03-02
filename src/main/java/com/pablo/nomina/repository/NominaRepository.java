package com.pablo.nomina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pablo.nomina.entity.Nomina;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, Long> {
}