package com.drei.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Tecnico findById(Integer id) {
        Optional<Tecnico> registro = repository.findById(id);
        return registro.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: "
                        + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO umTecnicoDTO) {
        if (this.findByCPF(umTecnicoDTO) != null) {
            throw new DataViolationIntegrityException("CPF já cadastrado na base de dados");
        }
        return repository.save(new Tecnico(null,
                umTecnicoDTO.getNome(),
                umTecnicoDTO.getCpf(),
                umTecnicoDTO.getTelefone()));
    }

    public Tecnico update(Integer id, TecnicoDTO umTecnicoDTO) {
        var novoTecnico = this.findById(id);
        var antigoTecnico = this.findByCPF(umTecnicoDTO);

        if (antigoTecnico != null && antigoTecnico.getId() != id) {
            throw new DataViolationIntegrityException("CPF já cadastrado na base de dados");
        }

        novoTecnico.setNome(umTecnicoDTO.getNome());
        novoTecnico.setCpf(umTecnicoDTO.getCpf());
        novoTecnico.setTelefone(umTecnicoDTO.getTelefone()); 

        return repository.save(novoTecnico);
    }

    private Tecnico findByCPF(TecnicoDTO umTecnicoDTO) {
        var umTecnico = repository.findByCPF(umTecnicoDTO.getCpf());
        return umTecnico != null ? umTecnico : null;
    }

}
