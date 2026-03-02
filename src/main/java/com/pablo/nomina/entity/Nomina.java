package com.pablo.nomina.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Nomina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double salarioBase;
    private Double bonificaciones;
    private Double deducciones;
    private Double totalPagar;
    private LocalDate fechaPago;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}