package com.drei.os.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.drei.os.dtos.TecnicoDTO;
import com.drei.os.services.TecnicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {
    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{inId}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer inId) {
        var tecnicoDTO = new TecnicoDTO(service.findById(inId));
        return ResponseEntity.ok().body(tecnicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        var listadeTecnicosDTOS = service.findAll()
                .stream()
                .map(tecnico -> new TecnicoDTO(tecnico))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listadeTecnicosDTOS);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO inTecnicoDTO) {
        var tecnico = service.create(inTecnicoDTO);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{inId}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer inId,
            @Valid @RequestBody TecnicoDTO inTecnicoDTO) {
        var tecnicoDTOatualizado = new TecnicoDTO(service.update(inId, inTecnicoDTO));
        return ResponseEntity.ok().body(tecnicoDTOatualizado);
    }

    @DeleteMapping(value = "/{inId}")
    public ResponseEntity<Void> delete(@PathVariable Integer inId){
        service.delete(inId);
        return ResponseEntity.noContent().build();
    }

}
