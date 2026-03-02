package com.pablo.nomina.entity;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Entity
@Data

public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String documento;

    @NotBlank
    private String cargo;

    @NotNull
    private Double salarioBase;

    private LocalDate fechaIngreso;

    private String estado; // ACTIVO / INACTIVO
}