package com.pablo.nomina.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pablo.nomina.entity.Nomina;
import com.pablo.nomina.service.NominaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/api/nomina")
@RequiredArgsConstructor
@Valid

public class NominaController {

    private final NominaService service;

    @GetMapping
    public Page<Nomina> listar(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.listar(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @GetMapping("/{id}")
    public Nomina obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public Nomina crear(@RequestBody Nomina nomina) {
        return service.guardar(nomina);
    }

    // 🔥 ESTE TE FALTABA
    @PutMapping("/{id}")
    public Nomina editar(@PathVariable Long id, @RequestBody Nomina nomina) {
        nomina.setId(id);
        return service.guardar(nomina);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}