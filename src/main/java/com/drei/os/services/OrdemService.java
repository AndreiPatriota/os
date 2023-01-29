package com.drei.os.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drei.os.domain.Ordem;
import com.drei.os.repositories.OrdemRepository;
import com.drei.os.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemService {
    @Autowired
    private OrdemRepository repository;

    public Ordem findById(Integer inIn) {
        var ordem = repository.findById(inIn);
        return ordem.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + inIn + ", Tipo: " + Ordem.class.getName()));
    }

    public List<Ordem> findAll() {
        return repository.findAll();
    }

}
