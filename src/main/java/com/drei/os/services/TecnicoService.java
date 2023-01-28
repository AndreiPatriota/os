package com.drei.os.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.drei.os.domain.Pessoa;
import com.drei.os.domain.Tecnico;
import com.drei.os.dtos.TecnicoDTO;
import com.drei.os.repositories.PessoaRepository;
import com.drei.os.repositories.TecnicoRepository;
import com.drei.os.services.exceptions.DataViolationIntegrityException;
import com.drei.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer inId) {
        var tecnico = tecnicoRepository.findById(inId);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + inId + ", Tipo: "
                        + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO inTecnicoDTO) {
        if (this.findByCPF(inTecnicoDTO) != null) {
            throw new DataViolationIntegrityException("CPF já cadastrado na base de dados");
        }
        return tecnicoRepository.save(new Tecnico(null,
                inTecnicoDTO.getNome(),
                inTecnicoDTO.getCpf(),
                inTecnicoDTO.getTelefone()));
    }

    public Tecnico update(Integer inIn, TecnicoDTO inTecnicoDTO) {
        var tecnico = this.findById(inIn);
        var pessoaTesteCpf = this.findByCPF(inTecnicoDTO);

        if (pessoaTesteCpf != null && pessoaTesteCpf.getId() != inIn) {
            throw new DataViolationIntegrityException("CPF já cadastrado na base de dados");
        }

        tecnico.setNome(inTecnicoDTO.getNome());
        tecnico.setCpf(inTecnicoDTO.getCpf());
        tecnico.setTelefone(inTecnicoDTO.getTelefone());

        return tecnicoRepository.save(tecnico);
    }

    public void delete(Integer id) {
        var tecnico = this.findById(id);
        if (tecnico.getListaDeOrdemdeServicos().size() > 0) {
            throw new DataIntegrityViolationException("Este técnico possui ordens de serviço atribuídas a ele. Não é possível deletá-lo!");
        }

        tecnicoRepository.delete(tecnico);
    }

    private Pessoa findByCPF(TecnicoDTO inTecnicoDTO) {
        var pessoa = pessoaRepository.findByCPF(inTecnicoDTO.getCpf());
        return pessoa != null ? pessoa : null;
    }

}
