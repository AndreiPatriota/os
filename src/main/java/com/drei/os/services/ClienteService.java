package com.drei.os.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drei.os.domain.Cliente;
import com.drei.os.domain.Pessoa;
import com.drei.os.dtos.ClienteDTO;
import com.drei.os.repositories.ClienteRepository;
import com.drei.os.repositories.PessoaRepository;
import com.drei.os.services.exceptions.DataViolationIntegrityException;
import com.drei.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer inId) {
        var cliente = repository.findById(inId);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + inId + ", Tipo: "
                        + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO inClienteDTO) {
        if(this.findByCPF(inClienteDTO) != null) {
            throw new DataViolationIntegrityException("CPF já cadastrado na base de dados");
        }
        return repository.save(new Cliente(null, 
                        inClienteDTO.getNome(), 
                        inClienteDTO.getCpf(),
                        inClienteDTO.getTelefone()));
    }

    private Pessoa findByCPF(ClienteDTO inClienteDTO) {
        var pessoa = pessoaRepository.findByCPF(inClienteDTO.getCpf());
        return pessoa != null ? pessoa : null;
    }

}
