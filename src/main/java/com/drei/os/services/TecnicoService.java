package com.drei.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drei.os.domain.Tecnico;
import com.drei.os.dtos.TecnicoDTO;
import com.drei.os.repositories.TecnicoRepository;
import com.drei.os.services.exceptions.DataViolationIntegrityException;
import com.drei.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer inId) {
        var tecnico = repository.findById(inId);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + inId + ", Tipo: "
                        + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO inTecnicoDTO) {
        if (this.findByCPF(inTecnicoDTO) != null) {
            throw new DataViolationIntegrityException("CPF já cadastrado na base de dados");
        }
        return repository.save(new Tecnico(null,
                inTecnicoDTO.getNome(),
                inTecnicoDTO.getCpf(),
                inTecnicoDTO.getTelefone()));
    }

    public Tecnico update(Integer inIn, TecnicoDTO inTecnicoDTO) {
        var tecnico = this.findById(inIn);
        var tecnicoTesteCpf = this.findByCPF(inTecnicoDTO);

        if (tecnicoTesteCpf != null && tecnicoTesteCpf.getId() != inIn) {
            throw new DataViolationIntegrityException("CPF já cadastrado na base de dados");
        }

        tecnico.setNome(inTecnicoDTO.getNome());
        tecnico.setCpf(inTecnicoDTO.getCpf());
        tecnico.setTelefone(inTecnicoDTO.getTelefone()); 

        return repository.save(tecnico);
    }

    private Tecnico findByCPF(TecnicoDTO inTecnicoDTO) {
        var tecnico = repository.findByCPF(inTecnicoDTO.getCpf());
        return tecnico != null ? tecnico : null;
    }

}
