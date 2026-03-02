package com.pablo.nomina.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.pablo.nomina.entity.Nomina;
import com.pablo.nomina.entity.Empleado;
import com.pablo.nomina.repository.NominaRepository;
import com.pablo.nomina.repository.EmpleadoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class NominaService {

    private final NominaRepository repository;
    private final EmpleadoRepository empleadoRepository;

    public Page<Nomina> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Nomina guardar(Nomina nomina) {

        if (nomina.getEmpleado() == null || nomina.getEmpleado().getId() == null) {
            throw new RuntimeException("Debe enviar el empleado con id");
        }

        Empleado empleado = empleadoRepository
                .findById(nomina.getEmpleado().getId())
                .orElseThrow(() -> new RuntimeException("Empleado no existe"));

        // 🔥 Tomar salario del empleado
        Double salario = empleado.getSalarioBase();

        Double bonos = nomina.getBonificaciones() != null ? nomina.getBonificaciones() : 0.0;
        Double descuentos = nomina.getDeducciones() != null ? nomina.getDeducciones() : 0.0;

        nomina.setEmpleado(empleado);
        nomina.setSalarioBase(salario);
        nomina.setTotalPagar(salario + bonos - descuentos);

        return repository.save(nomina);
    }

    public Nomina obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nomina no encontrada"));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}