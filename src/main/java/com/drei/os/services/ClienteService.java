package com.drei.os.services;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer inId) {
        var cliente = clienteRepository.findById(inId);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + inId + ", Tipo: "
                        + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDTO inClienteDTO) {
        if (this.findByCPF(inClienteDTO) != null) {
            throw new DataViolationIntegrityException("CPF já cadastrado na base de dados");
        }
        return clienteRepository.save(new Cliente(null,
                inClienteDTO.getNome(),
                inClienteDTO.getCpf(),
                inClienteDTO.getTelefone()));
    }

    public Cliente update(Integer inIn, ClienteDTO inClienteDTO) {
        var cliente = this.findById(inIn);
        var pessoa = pessoaRepository.findByCPF(inClienteDTO.getCpf());
        Function<Pessoa, Boolean> cpfJaExiste = (p) -> p != null && p.getId() != inIn;

        if (cpfJaExiste.apply(pessoa)) {
            throw new DataViolationIntegrityException("CPF já cadastrado na base de dados");
        }

        cliente.setNome(inClienteDTO.getNome());
        cliente.setCpf(inClienteDTO.getCpf());
        cliente.setTelefone(inClienteDTO.getTelefone());

        return clienteRepository.save(cliente);
    }

    public void delete(Integer inId) {
        var cliente = this.findById(inId);
        if (cliente.temOrdemdeServico()) {
            throw new DataIntegrityViolationException(
                    "Este cliente possui ordens de serviço atribuídas a ele. Não é possível deletá-lo!");
        }

        clienteRepository.delete(cliente);
        return;
    }

    private Pessoa findByCPF(ClienteDTO inClienteDTO) {
        var pessoa = pessoaRepository.findByCPF(inClienteDTO.getCpf());
        return pessoa != null ? pessoa : null;
    }

}
