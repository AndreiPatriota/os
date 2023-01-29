package com.drei.os.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drei.os.domain.Ordem;
import com.drei.os.domain.enums.Prioridade;
import com.drei.os.domain.enums.Status;
import com.drei.os.dtos.OrdemDTO;
import com.drei.os.repositories.OrdemRepository;
import com.drei.os.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemService {
    @Autowired
    private OrdemRepository repository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TecnicoService tecnicoService;

    public Ordem findById(Integer inIn) {
        var ordem = repository.findById(inIn);
        return ordem.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + inIn + ", Tipo: " + Ordem.class.getName()));
    }

    public List<Ordem> findAll() {
        return repository.findAll();
    }

    public Ordem create(OrdemDTO inOrdemDTO) {
        var tecnico = tecnicoService.findById(inOrdemDTO.getIdTecnico());
        var cliente = clienteService.findById(inOrdemDTO.getIdCliente());
        var ordem = new Ordem(null,
                            Prioridade.toEnum(inOrdemDTO.getPrioridade()),
                            inOrdemDTO.getObservacoes(),
                            Status.toEnum(inOrdemDTO.getStatus()),
                            tecnico,
                            cliente);
        
        return repository.save(ordem);
    }

}
