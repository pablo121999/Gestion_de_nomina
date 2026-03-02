package com.pablo.nomina.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.pablo.nomina.entity.Empleado;
import com.pablo.nomina.repository.EmpleadoRepository;
import com.pablo.nomina.exception.ResourceNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public Page<Empleado> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Empleado obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado"));
    }

    public Empleado guardar(Empleado empleado) {
        return repository.save(empleado);
    }

    public void eliminar(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("No se puede eliminar el empleado porque tiene nóminas asociadas");
        }
    }
}