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

import com.drei.os.dtos.ClienteDTO;
import com.drei.os.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{inId}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer inId) {
        var clienteDTO = new ClienteDTO(service.findById(inId));
        return ResponseEntity.ok().body(clienteDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        var listadeClinetesDTO = service.findAll()
                .stream()
                .map((cliente) -> new ClienteDTO(cliente))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listadeClinetesDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO inClienteDTO) {
        var cliente = service.create(inClienteDTO);
        var uri = ServletUriComponentsBuilder
                    .fromCurrentRequest() //caminho http://host/clientes
                    .path("/{id}")  //adiciona /{id} ao caminho
                    .buildAndExpand(cliente.getId()) //substitui id por um valor
                    .toUri(); //cria um obj URI
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{inIn}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer inIn, @RequestBody ClienteDTO inClienteDTO) {
        var novoClienteDTO = new ClienteDTO(service.update(inIn, inClienteDTO));
        return ResponseEntity.ok().body(novoClienteDTO);
    }

    @DeleteMapping(value = "/{inId}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer inId) {
        service.delete(inId);
        return ResponseEntity.noContent().build();
    }

}
