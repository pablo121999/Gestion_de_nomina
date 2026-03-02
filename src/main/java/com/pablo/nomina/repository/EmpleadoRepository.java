package com.pablo.nomina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pablo.nomina.entity.Empleado;


public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}