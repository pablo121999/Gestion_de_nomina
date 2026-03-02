package com.pablo.nomina.controller;

import com.pablo.nomina.entity.Empleado;
import com.pablo.nomina.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoService service;

    @GetMapping
    public Page<Empleado> listar(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.listar(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @GetMapping("/{id}")
    public Empleado obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public Empleado crear(@Valid @RequestBody Empleado empleado) {
        return service.guardar(empleado);
    }

    @PutMapping("/{id}")
    public Empleado editar(@PathVariable Long id, @RequestBody Empleado empleado) {
        empleado.setId(id);
        return service.guardar(empleado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}