package com.drei.os.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drei.os.domain.Ordem;
import com.drei.os.domain.enums.Prioridade;
import com.drei.os.domain.enums.Status;
import com.drei.os.dtos.AtualizaOrdemDTO;
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
                inOrdemDTO.getPrioridade(),
                inOrdemDTO.getObservacoes(),
                inOrdemDTO.getStatus(),
                tecnico,
                cliente);

        if (ordem.getStatus().getCodigo().equals(2)) {
            ordem.setDataFechamento(LocalDateTime.now());
        }

        return repository.save(ordem);
    }

    public Ordem update(AtualizaOrdemDTO inOrdemDTO, Integer inId) {
        var ordem = this.findById(inId);

        ordem.setStatus(inOrdemDTO.getStatus());
        ordem.setPrioridade(inOrdemDTO.getPrioridade() != null
                ? inOrdemDTO.getPrioridade()
                : ordem.getPrioridade());
        ordem.setObservacoes(inOrdemDTO.getObservacoes() != null
                ? inOrdemDTO.getObservacoes()
                : ordem.getObservacoes());
        ordem.setCliente(inOrdemDTO.getIdCliente() != null
                ? clienteService.findById(inOrdemDTO.getIdCliente())
                : ordem.getCliente());
        ordem.setTecnico(inOrdemDTO.getIdTecnico() != null
                ? tecnicoService.findById(inOrdemDTO.getIdTecnico())
                : ordem.getTecnico());

        if (ordem.getStatus().getCodigo().equals(2)) {
            ordem.setDataFechamento(LocalDateTime.now());
        } else {
            ordem.setDataFechamento(null);
        }

        return repository.save(ordem);
    }

}
